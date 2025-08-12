package com.example.spring_juyoung.book.controller;

import com.example.spring_juyoung.account.repository.AccountRepository;
import com.example.spring_juyoung.book.controller.request.BookRegisterRequestForm;
import com.example.spring_juyoung.book.controller.request.ListBookRequestForm;
import com.example.spring_juyoung.book.controller.response.BookRegisterResponseForm;
import com.example.spring_juyoung.book.controller.response.ListBookResponseForm;
import com.example.spring_juyoung.book.service.BookService;
import com.example.spring_juyoung.book.service.request.ListBookRequest;
import com.example.spring_juyoung.book.service.request.RegisterBookRequest;
import com.example.spring_juyoung.book.service.response.ListBookResponse;
import com.example.spring_juyoung.book.service.response.RegisterBookResponse;
import com.example.spring_juyoung.redis_cache.RedisCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/book")

public class BookController {
    @Autowired
     BookService bookService;
    @Autowired
    RedisCacheService redisCacheService;
    @Autowired
    AccountRepository accountRepository;

    @PostMapping("/register")
    public BookRegisterResponseForm responseForm(
            @RequestHeader("Authorization") String code,
            @RequestBody BookRegisterRequestForm requestForm){

        // 인증 정보 체크
        String userToken = code.replace("Bearer", "").trim();
        Long accountId = redisCacheService.getValueByKey(userToken, Long.class);

        // 요청 정보를 가지고 Book 엔티티 구성
        RegisterBookRequest request = requestForm.toBookRegisterRequest();
        RegisterBookResponse response = bookService.register(request, accountId);

        //책 등록이 잘 되었는지 read 혹은 필요에 따라 list에 적합한 응답 구성
        return BookRegisterResponseForm.from(response);
    }

    @GetMapping("/list")
    public ListBookResponseForm bookList(@ModelAttribute ListBookRequestForm requestForm){
        log.info("bookList() -> requestForm: {}", requestForm);

        ListBookRequest request = requestForm.toListBookRequest();
        ListBookResponse response = bookService.list(request);

        return ListBookResponseForm.from(response);
    }

}
