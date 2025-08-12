package com.example.spring_juyoung.book.service.response;

import com.example.spring_juyoung.book.entity.Book;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor

public class RegisterBookResponse {
    final private String title;
    final private String author;
    final private String content;
    final private int price;
    final private String isbn;


    public static RegisterBookResponse from(Book createdBook) {
        return new RegisterBookResponse(createdBook.getTitle(), createdBook.getAuthor(),
        createdBook.getContent(), createdBook.getPrice(),createdBook.getIsbn());

    }
}
