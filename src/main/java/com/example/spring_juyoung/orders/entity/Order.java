package com.example.spring_juyoung.orders.entity;

import com.example.spring_juyoung.account.entity.Account;
import com.example.spring_juyoung.book.entity.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@RequiredArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    private int quantity;
    private int order_date;
    private int total_price;

    public Order(Book book, Account account, int quantity, int order_date, int total_price) {
        this.book = book;
        this.account = account;
        this.quantity = quantity;
        this.order_date = order_date;
        this.total_price = total_price;
    }


    @CreationTimestamp
    private LocalDateTime created;

//    public Order(Book book, Long quantity, Long price, Order order) {
//    }
//
//    public Order(Book book, Account account, int quantity, int orderDate, int totalPrice) {
//    }
public Order(Account account) {
        this.account = account;
    }

}

