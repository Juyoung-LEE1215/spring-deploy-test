package com.example.spring_juyoung.kakao_authentication.service.response;

import com.example.spring_juyoung.kakao_authentication.cotroller.response_form.KakaoUserInfoResponseForm;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class KakaoUserInfoResponse {
    final private String email;
    final private String nickname;
    final private String accessToken;
    final private Boolean isNewUser;

    public static KakaoUserInfoResponse from(String email, String nickname, String accessToken, Boolean isNewUser) {
        return new KakaoUserInfoResponse(
                email,
                nickname,
                accessToken,
                isNewUser
        );
    }
}
