package com.example.spring_juyoung.kakao_authentication.service;

import com.example.spring_juyoung.kakao_authentication.service.response.KakaoUserInfoResponse;

public interface KakaoAuthenticationService {
    KakaoUserInfoResponse handleLogin(String code);

}
