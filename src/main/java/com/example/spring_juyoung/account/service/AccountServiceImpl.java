package com.example.spring_juyoung.account.service;

import com.example.spring_juyoung.account.entity.Account;
import com.example.spring_juyoung.account.repository.AccountRepository;
import com.example.spring_juyoung.account.service.request.AccountRegisterRequest;
import com.example.spring_juyoung.account.service.response.AccountRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AccountServiceImpl implements AccountService {
    final private AccountRepository accountRepository;

    @Override
    public AccountRegisterResponse register(AccountRegisterRequest request) {
        Account requestedAccount = request.toAccount();
        Account createdAccount = accountRepository.save(requestedAccount);

        return AccountRegisterResponse.from(createdAccount);
    }



}
