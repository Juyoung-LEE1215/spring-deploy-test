package com.example.spring_juyoung.book.controller.request;

import com.example.spring_juyoung.book.service.request.ListBookRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class ListBookRequestForm {

    final private Integer page;
    final private Integer perPage;

    public ListBookRequest toListBookRequest(){
        return new ListBookRequest(page,perPage);
    }
}
