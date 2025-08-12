package com.example.spring_juyoung.orders.services.request;

import com.example.spring_juyoung.account.entity.Account;
import com.example.spring_juyoung.book.entity.Book;
import com.example.spring_juyoung.orders.entity.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BookOrderRequest {
    private Long bookId;
    private int quantity;
    private int order_date;
    private int total_price;
    private Long accountId;

    public BookOrderRequest(Long bookId, int quantity, int orderDate, int totalPrice, Long accountId) {
    }

    public Order toOrder(Book book, Account account) {
        return new Order(book, account, quantity, order_date, total_price);

    }



}
