package com.example.spring_juyoung.cart.service.response;

import com.example.spring_juyoung.book.entity.Book;
import com.example.spring_juyoung.cart.entity.Cart;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CartResponse {
    private final Long bookId;
    private final String bookTitle;
    private final int price;
    private final int quantity;

    public static CartResponse from(Cart savedCart) {
        Book cartBook = savedCart.getBook();

        return new CartResponse(
                savedCart.getId(),
                cartBook.getTitle(),
                cartBook.getPrice(),
                savedCart.getPrice()

        );
    }
}
