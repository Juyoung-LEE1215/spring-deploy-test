package com.example.spring_juyoung.cart.controller.response;

import com.example.spring_juyoung.cart.service.response.CartResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResponseForm {
    private final Long bookId;
    private final String bookTitle;
    private final int price;
    private final int quantity;


    public static ResponseForm from(CartResponse response) {
        return new ResponseForm(
                response.getBookId(),
                response.getBookTitle(),
                response.getPrice(),
                response.getQuantity());
    }
}
