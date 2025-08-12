package com.example.spring_juyoung.orders.cotroller.response;

import com.example.spring_juyoung.orders.services.response.CreateAllOrderResponse;
import com.example.spring_juyoung.orders.services.response.CreateOrderItemResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class CreateAllOrdersResponseForm {
    final private Long orderId;
    final private int itemsCount;
    final private List<CreateOrderItemResponse> itemList;

    public static CreateAllOrdersResponseForm from(CreateAllOrderResponse response) {
        return new CreateAllOrdersResponseForm(
                response.getOrderId(),
                response.getItemsCount(),
                response.getItemList()
        );
    }
}
