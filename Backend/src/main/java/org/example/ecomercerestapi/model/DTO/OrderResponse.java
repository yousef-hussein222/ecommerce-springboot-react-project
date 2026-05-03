package org.example.ecomercerestapi.model.DTO;

import java.time.LocalDate;
import java.util.List;

public record OrderResponse(
        String orderId,
        String customerName,
        String email,
        String status,
        LocalDate localDate,
        List<OrderItemResponse> items
) {
}
