package com.example.spring_juyoung.orders.services.request;

import com.example.spring_juyoung.book.entity.Book;
import com.example.spring_juyoung.orders.entity.Order;
import com.example.spring_juyoung.orders.entity.OrderItem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateOrderItemRequest {

    final private Long bookId;
    final private Long quantity;
    final private Long price;

    public OrderItem toOrderItem(Book book, Order order) {
        return new OrderItem (book,quantity,price,order);
    }
}
