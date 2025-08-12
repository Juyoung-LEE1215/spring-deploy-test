package com.example.spring_juyoung.account.service.request;

import com.example.spring_juyoung.account.entity.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccountRegisterRequest {
    final String email;
    final String nickname;

    public Account toAccount() {
        return new Account(email, nickname);
    }
}
