package com.example.spring_juyoung.book.service.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ListBookRequest {
    final private Integer page;
    final private Integer perPage;
}
