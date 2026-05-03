package org.example.ecomercerestapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.ecomercerestapi.model.DTO.OrderRequest;
import org.example.ecomercerestapi.model.DTO.OrderResponse;
import org.example.ecomercerestapi.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/orders/place")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.placeOrder(orderRequest);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return orderService.getAllOrdersResponses();
    }

}
