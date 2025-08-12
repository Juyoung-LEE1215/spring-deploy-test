package com.example.spring_juyoung.book.entity;

import com.example.spring_juyoung.account.entity.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String content;
    private int price;
    private String isbn;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Book(String title, String author, String content, int price, String isbn, Account account) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.price = price;
        this.isbn = isbn;
    }


}
