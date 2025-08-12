package com.example.spring_juyoung.account.service;

import com.example.spring_juyoung.account.service.request.AccountRegisterRequest;
import com.example.spring_juyoung.account.service.response.AccountRegisterResponse;

public interface AccountService {

    AccountRegisterResponse register (AccountRegisterRequest request);
}
