package com.example.spring_juyoung.book.service;

import com.example.spring_juyoung.book.service.request.ListBookRequest;
import com.example.spring_juyoung.book.service.request.RegisterBookRequest;
import com.example.spring_juyoung.book.service.response.ListBookResponse;
import com.example.spring_juyoung.book.service.response.RegisterBookResponse;


public interface BookService {
    RegisterBookResponse register(RegisterBookRequest request, Long accountId);

    ListBookResponse list(ListBookRequest request);

}
