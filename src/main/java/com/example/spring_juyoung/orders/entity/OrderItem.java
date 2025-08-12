package com.example.spring_juyoung.orders.entity;

import com.example.spring_juyoung.book.entity.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//다중주문 => 수량,항목(bookId),가격,어디서 주문한지(orderId)

@Entity
@Getter
@NoArgsConstructor

public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private Long quantity;
    private Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;


    public OrderItem(Book book, Long quantity, Long price, Order order) {
        this.book = book;
        this.quantity = quantity;
        this.price = price;
        this.order = order;
    }
    public Order getOrder() {
        return order;
    }
}

