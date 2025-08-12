package com.example.spring_juyoung.orders.services.response;

import com.example.spring_juyoung.orders.entity.Order;
import com.example.spring_juyoung.orders.entity.OrderItem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class CreateAllOrderResponse {
    final private long orderId;
    final private int itemsCount;
    final private List<CreateOrderItemResponse> itemList;

    public static CreateAllOrderResponse from(Order order, List<OrderItem> savedOrderItems) {

        List<CreateOrderItemResponse> itemsResponse = savedOrderItems.stream()
                .map(CreateOrderItemResponse :: from)
                .collect(Collectors.toList());

        return new CreateAllOrderResponse(
                order.getId(),
                itemsResponse.size(),
                itemsResponse
        );

    }
}
