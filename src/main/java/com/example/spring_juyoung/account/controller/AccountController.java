package com.example.spring_juyoung.account.controller;

import com.example.spring_juyoung.account.controller.request.AccountRegisterRequestForm;
import com.example.spring_juyoung.account.controller.response.AccountRegisterResponseForm;
import com.example.spring_juyoung.account.repository.AccountRepository;
import com.example.spring_juyoung.account.service.AccountService;
import com.example.spring_juyoung.account.service.request.AccountRegisterRequest;
import com.example.spring_juyoung.account.service.response.AccountRegisterResponse;
import com.example.spring_juyoung.redis_cache.RedisCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    final private AccountService accountService;
    final private RedisCacheService redisCacheService;
    private final AccountRepository accountRepository;

    @PostMapping("/register")
    public AccountRegisterResponseForm register(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody AccountRegisterRequestForm requestForm) {

        String temporaryUserToken = authorizationHeader.replace("Bearer ", "").trim();
        String accessToken = redisCacheService.getValueByKey(temporaryUserToken,String.class);
                if(accessToken==null){
                    throw new IllegalArgumentException("만료되었거나 잘못된 코드 요청입니다.");
                }

        // 회원가입 진행
        AccountRegisterRequest request = requestForm.toAccountRegisterRequest();
        AccountRegisterResponse response = accountService.register(request);
        Long accountId = response.getAccountId();

        //실제 토큰 발급
        String userToken = UUID.randomUUID().toString();
        redisCacheService.setKeyAndValue(userToken, accountId);
        redisCacheService.setKeyAndValue(accountId, userToken);

        //임시토큰 삭제
        redisCacheService.deleteByKey(temporaryUserToken);
       return AccountRegisterResponseForm.from(response,userToken);
    }


}