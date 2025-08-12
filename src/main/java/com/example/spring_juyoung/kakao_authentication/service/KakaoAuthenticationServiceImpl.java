package com.example.spring_juyoung.kakao_authentication.service;

import com.example.spring_juyoung.account.entity.Account;
import com.example.spring_juyoung.account.repository.AccountRepository;
import com.example.spring_juyoung.kakao_authentication.repository.KakaoAuthenticationRepository;
import com.example.spring_juyoung.kakao_authentication.service.response.KakaoUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KakaoAuthenticationServiceImpl implements KakaoAuthenticationService {
    final private KakaoAuthenticationRepository kakaoAuthenticationRepository;
    private final AccountRepository accountRepository;

    @Override
    public KakaoUserInfoResponse handleLogin(String code) {
        // Map<String, Object> getAccessToken(String code);
        Map<String, Object> tokenResponse = kakaoAuthenticationRepository.getAccessToken(code);
        String accessToken = tokenResponse.get("access_token").toString();

        // Map<String, Object> getUserInfo(String accessToken);
        Map<String, Object> userInfoResponse = kakaoAuthenticationRepository.getUserInfo(accessToken);
        Map<?, ?> userInfoProperties = (Map<?, ?>) userInfoResponse.get("properties");
        String nickname = (String) (userInfoProperties).get("nickname");

        Map<?, ?> userInfoKakaoAccount = (Map<?, ?>) userInfoResponse.get("kakao_account");
        String email = (String) (userInfoKakaoAccount).get("email");

        Optional<Account> maybeAccount = accountRepository.findByEmail(email);
        boolean isNewUser = false;

        if (maybeAccount.isEmpty()) {
            isNewUser = true;
        }

        return KakaoUserInfoResponse.from(email, nickname, accessToken,isNewUser);
    }

}
