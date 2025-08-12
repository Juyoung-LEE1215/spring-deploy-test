package com.example.spring_juyoung.book.service.request;

import com.example.spring_juyoung.account.entity.Account;
import com.example.spring_juyoung.book.entity.Book;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterBookRequest {
    final private String title;
    final private String author;
    final private String content;
    final private int price;
    final private String isbn;

    public Book toBook(Account account) {

        return new Book(title, author, content, price, isbn, account);
    }
}
