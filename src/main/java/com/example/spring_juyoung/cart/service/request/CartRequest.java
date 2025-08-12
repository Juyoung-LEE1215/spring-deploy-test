package com.example.spring_juyoung.cart.service.request;

import com.example.spring_juyoung.account.entity.Account;
import com.example.spring_juyoung.book.entity.Book;
import com.example.spring_juyoung.cart.entity.Cart;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CartRequest {
    private final Long bookId;
    private final Long accountId;
    private final int price;
    private final int quantity;

public Cart toCart(Book book, Account account) {
    return new Cart(book, account, price, quantity);
}
}
