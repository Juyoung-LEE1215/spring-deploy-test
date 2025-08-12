package com.example.spring_juyoung.book.controller.request;

import com.example.spring_juyoung.book.service.request.RegisterBookRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BookRegisterRequestForm {
    final private Long id;
    final private String title;
    final private String author;
    final private String content;
    final private int price;
    final private String isbn;

    public RegisterBookRequest toBookRegisterRequest() {
        return new RegisterBookRequest(title, author, content, price, isbn);
    }
}
