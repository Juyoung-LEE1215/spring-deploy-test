package com.example.spring_juyoung.cart.controller.request;

import com.example.spring_juyoung.cart.service.request.CartRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RequestForm {
    private Long bookId;
    private int quantity;
    private int price;


    public CartRequest toRequestCart(Long accountId) {
        return new CartRequest(bookId,accountId,price,quantity);
    }
}
