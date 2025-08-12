package com.example.spring_juyoung.orders.services.response;

import com.example.spring_juyoung.book.entity.Book;
import com.example.spring_juyoung.orders.entity.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BookOrderResponse {
    private Long bookId;
    private String bookTitle;
    private int quantity;
    private int order_date;
    private int total_price;

    public BookOrderResponse(Long id, String title, int quantity, int orderDate, int totalPrice) {
        this.bookId = id;
        this.bookTitle = title;
        this.quantity = quantity;
        this.order_date = orderDate;
        this.total_price = totalPrice;
    }


    public static BookOrderResponse from(Order createdOrder) {
        Book orderedBook = createdOrder.getBook();

        return new BookOrderResponse(
                orderedBook.getId(),
                orderedBook.getTitle(),
                createdOrder.getQuantity(),
                createdOrder.getOrder_date(),
                createdOrder.getTotal_price()

        );
    }
}
