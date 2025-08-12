package com.example.spring_juyoung.orders.cotroller.response;

import com.example.spring_juyoung.orders.services.response.BookOrderResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BookOrderResponseForm {
    private Long bookId;
    private String bookTitle;
    private int quantity;
    private int order_date;
    private int total_price;


    public BookOrderResponseForm(Long bookId, String bookTitle, int quantity, int orderDate, int totalPrice) {
    }


    public static BookOrderResponseForm from(BookOrderResponse response) {
        return new BookOrderResponseForm(
                response.getBookId(),
                response.getBookTitle(),
                response.getQuantity(),
                response.getOrder_date(),
                response.getTotal_price()
        );
    }
}
