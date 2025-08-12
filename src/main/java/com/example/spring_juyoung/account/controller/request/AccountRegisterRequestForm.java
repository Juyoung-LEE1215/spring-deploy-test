package com.example.spring_juyoung.account.controller.request;

import com.example.spring_juyoung.account.service.request.AccountRegisterRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccountRegisterRequestForm {
    final private String email;
    final private String nickname;

    public AccountRegisterRequest toAccountRegisterRequest(){
        return new AccountRegisterRequest(email,nickname);
    }
}
