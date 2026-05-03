package org.example.ecomercerestapi.service;

import lombok.RequiredArgsConstructor;
import org.example.ecomercerestapi.model.DTO.*;
import org.example.ecomercerestapi.model.*;
import org.example.ecomercerestapi.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;


    public ResponseEntity<OrderResponse> placeOrder(OrderRequest orderRequest) {
        Order order = new Order();

        String orderId = "ORD" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        order.setOrderId(orderId);
        order.setCustomerName(orderRequest.customerName());
        order.setEmail(orderRequest.email());
        order.setStatus("PLACED");
        order.setOrderDate(LocalDate.now());

        //------------------------------------------

        List<OrderItemRequest> orderItemRequests = orderRequest.items();
        List<OrderItem> orderItems = new ArrayList<>();
        List<OrderItemResponse> orderItemResponses = new ArrayList<>();
        for (OrderItemRequest orderItemRequest : orderItemRequests) {
            Product product = productRepo.findById(orderItemRequest.productId()).orElseThrow();
            OrderItem orderItem = new OrderItem();

            if (product.getStockQuantity() >= orderItemRequest.quantity()) {
                BigDecimal price = product.getPrice().multiply(BigDecimal.valueOf(orderItemRequest.quantity()));
                orderItem.setProduct(product);
                orderItem.setQuantity(orderItemRequest.quantity());
                orderItem.setTotalPrice(price);
                orderItem.setOrder(order);
                Integer remainQuantity = product.getStockQuantity() - orderItemRequest.quantity();
                product.setStockQuantity(remainQuantity);
                productRepo.save(product);


                orderItems.add(orderItem);

                OrderItemResponse orderItemResponse = new OrderItemResponse(
                        product.getName(),
                        orderItemRequest.quantity(),
                        price);

                orderItemResponses.add(orderItemResponse);
            }
        }
        order.setItems(orderItems);
        orderRepo.save(order);

        // built order response part

        OrderResponse orderResponse = new OrderResponse(order.getOrderId(),
                order.getCustomerName(),
                order.getEmail(),
                order.getStatus(),
                order.getOrderDate(),
                orderItemResponses);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    public ResponseEntity<List<OrderResponse>> getAllOrdersResponses() {
        List<Order> orders = orderRepo.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orders) {
            List<OrderItemResponse> orderItemResponses = new ArrayList<>();
            for (OrderItem orderItem : order.getItems()) {
                OrderItemResponse orderItemResponse = new OrderItemResponse(
                        orderItem.getProduct().getName(),
                        orderItem.getQuantity(),
                        orderItem.getTotalPrice()
                );
                orderItemResponses.add(orderItemResponse);
            }
            System.out.println(orderItemResponses);
            OrderResponse orderResponse = new OrderResponse(
                    order.getOrderId(),
                    order.getCustomerName(),
                    order.getEmail(),
                    order.getStatus(),
                    order.getOrderDate(),
                    orderItemResponses
            );
            orderResponses.add(orderResponse);

        }
        return new ResponseEntity<>(orderResponses, HttpStatus.OK);
    }
}
