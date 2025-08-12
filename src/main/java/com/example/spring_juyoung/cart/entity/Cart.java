package com.example.spring_juyoung.cart.entity;


import com.example.spring_juyoung.account.entity.Account;
import com.example.spring_juyoung.book.entity.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="bookId", nullable=false)
    final private Book book;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="accountId",nullable=false)
    final private Account account;

    final private int price;
    final private int quantity;

    public Cart(Book book, Account account, int price, int quantity) {
        this.book = book;
        this.account = account;
        this.price = price;
        this.quantity = quantity;
    }
}
