package com.example.sbpostgresqltutorial.Order;

import com.example.sbpostgresqltutorial.Order.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface OrderRepository extends JpaRepository<Order, UUID> {
}
