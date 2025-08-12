package com.example.spring_juyoung.orders.cotroller.request;

import com.example.spring_juyoung.orders.services.request.CreateOrderItemRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateOrderItemRequestForm {
    final private Long bookId;
    final private Long quantity;
    final private Long price;

    public CreateOrderItemRequest toCreateOrderItemRequest(){
        return new CreateOrderItemRequest(bookId,quantity,price);
    }
}
