package com.example.spring_juyoung.orders.cotroller.request;

import com.example.spring_juyoung.orders.services.request.BookOrderRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BookOrderRequestForm {
    private Long bookId;
    private int quantity;
    private int order_date;
    private int total_price;

    public BookOrderRequest toBookOrderRequest(Long accountId) {
        return new BookOrderRequest(bookId,quantity,order_date,total_price,accountId);
    }
}
