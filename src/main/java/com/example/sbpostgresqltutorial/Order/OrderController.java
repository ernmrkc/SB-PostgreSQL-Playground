package com.example.sbpostgresqltutorial.Order;

import com.example.sbpostgresqltutorial.Order.Model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable String id){
        return ResponseEntity.ok(orderRepository.findById(UUID.fromString(id)).get());
    }

    @PostMapping
    public ResponseEntity<Void> createOrder(){
        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setTotal(19.90);
        orderRepository.save(order);
        return ResponseEntity.ok().build();
    }
}
