package com.example.sbpostgresqltutorial.Security;

import com.example.sbpostgresqltutorial.Security.Model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomUserRepository extends JpaRepository<CustomUser, String> {
}
