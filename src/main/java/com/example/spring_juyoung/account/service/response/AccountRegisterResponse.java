package com.example.spring_juyoung.account.service.response;

import com.example.spring_juyoung.account.entity.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccountRegisterResponse {
    final private Long accountId;
    final private String email;
    final private String nickname;



    public static AccountRegisterResponse from ( Account account) {
        return new AccountRegisterResponse(
                account.getAccount_id(),
                account.getEmail(),
                account.getNickname());
    }

}
