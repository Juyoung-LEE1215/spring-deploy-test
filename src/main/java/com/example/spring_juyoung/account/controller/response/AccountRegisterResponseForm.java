package com.example.spring_juyoung.account.controller.response;

import com.example.spring_juyoung.account.service.response.AccountRegisterResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccountRegisterResponseForm {
    final private String userToken;
    final private String email;
    final private String nickname;



    public static AccountRegisterResponseForm from (final AccountRegisterResponse response, String userToken) {
        return new AccountRegisterResponseForm (
                userToken,
                response.getEmail(),
                response.getNickname ()
                );
    }




}
