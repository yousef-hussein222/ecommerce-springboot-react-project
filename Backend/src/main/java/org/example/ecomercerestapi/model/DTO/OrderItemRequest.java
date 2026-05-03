package org.example.ecomercerestapi.model.DTO;

public record OrderItemRequest(
        int productId,
        int quantity
) {

}
