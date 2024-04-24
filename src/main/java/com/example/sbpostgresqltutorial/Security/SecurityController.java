package com.example.sbpostgresqltutorial.Security;

import com.example.sbpostgresqltutorial.Helpers.JwtUtil;
import com.example.sbpostgresqltutorial.Security.Model.CustomUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SecurityController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserRepository customUserRepository;
    private final BCryptPasswordEncoder encoder;

    public SecurityController(AuthenticationManager authenticationManager,
                              CustomUserRepository customUserRepository,
                              BCryptPasswordEncoder encoder) {
        this.authenticationManager = authenticationManager;
        this.customUserRepository = customUserRepository;
        this.encoder = encoder;
    }

    @GetMapping("/open")
    public String open(){
        return "No login required";
    }

    @GetMapping("/closed")
    public String closed(){
        return "Login is required";
    }

    @GetMapping("/special")
    public String special(){
        return "SPECIAL";
    }

    @GetMapping("/basic")
    public String basic(){
        return "BASIC";
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "Admin Page";
    }

    @GetMapping("/user")
    public String userPage(){
        return "User Page";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/preAuthAdmin")
    public String preAuthAdmin(){
        return "PreAuthorize for Admin Panel";
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/preAuthUser")
    public String preAuthUser(){
        return "PreAuthorize for User Panel";
    }

    @GetMapping("/validateToken/{token}")
    public String validateToken(@PathVariable String token){
        boolean retValue = JwtUtil.validateToken(token);
        if(retValue){
            return "True";
        }else {
            return "False";
        }
    }

    @GetMapping("/extractUsername/{token}")
    public String extractUsername(@PathVariable String token){
        return JwtUtil.extractUsername(token);
    }

    @GetMapping("/validateTokenWithUsername/{token}")
    public String validateTokenWithUsername(@PathVariable String token, @RequestParam(value = "username") String username){
        boolean retValue = JwtUtil.validateTokenWithUsername(token, username);
        if(retValue) return "True";
        return "false";
    }

    @PostMapping("/generateJWT")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = JwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwtToken));

    }

    @PostMapping("/createNewUser")
    public ResponseEntity<?> createNewUser(@RequestBody LoginRequest request){
        Optional<CustomUser> customUserOptional = customUserRepository.findById(request.getUsername());
        if(customUserOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User name already exist");
        }
        CustomUser user = new CustomUser();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        customUserRepository.save(user);
        return ResponseEntity.ok("Successful");
    }

}
