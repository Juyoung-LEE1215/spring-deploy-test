package com.example.spring_juyoung.orders.services.response;

import com.example.spring_juyoung.book.entity.Book;
import com.example.spring_juyoung.orders.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class CreateOrderItemResponse {
    private Long bookId;
    private String bookTitle;
    private Long quantity;
    private Long price;


    public static CreateOrderItemResponse from(OrderItem savedItem) {
        Book orderedBook = savedItem.getBook();

        return new CreateOrderItemResponse(
                orderedBook.getId(),
                orderedBook.getTitle(),
                savedItem.getQuantity(),
                savedItem.getPrice()

        );


    }
}
