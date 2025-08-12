package com.example.spring_juyoung.book.controller.response;

import com.example.spring_juyoung.book.service.response.RegisterBookResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BookRegisterResponseForm {
    final private String title;
    final private String author;
    final private String content;
    final private int price;
    final private String isbn;

    public static BookRegisterResponseForm from(RegisterBookResponse response) {
        return new BookRegisterResponseForm(
                response.getTitle(),
                response.getAuthor(),
                response.getContent(),
                response.getPrice(),
                response.getIsbn()
        );
    }

}
