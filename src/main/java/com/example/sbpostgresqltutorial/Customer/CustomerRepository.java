package com.example.sbpostgresqltutorial.Customer;

import com.example.sbpostgresqltutorial.Customer.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
