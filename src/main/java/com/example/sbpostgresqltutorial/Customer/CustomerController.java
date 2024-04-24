package com.example.sbpostgresqltutorial.Customer;

import com.example.sbpostgresqltutorial.Customer.Model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;
    public CustomerController(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id){
        return ResponseEntity.ok(customerRepository.findById(id).get());
    }

    @PostMapping
    public ResponseEntity updateCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
        return ResponseEntity.ok().build();
    }
}
