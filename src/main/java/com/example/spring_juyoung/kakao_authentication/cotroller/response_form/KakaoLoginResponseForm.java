package com.example.spring_juyoung.kakao_authentication.cotroller.response_form;


import com.example.spring_juyoung.kakao_authentication.service.response.KakaoUserInfoResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class KakaoLoginResponseForm {
    final String email;
    final String nickname;
    final String userToken;
    final Boolean isNewUser;

    public static KakaoLoginResponseForm from(
            KakaoUserInfoResponse kakaoUserInfoResponse,
            String temporaryUserToken) {

        return new KakaoLoginResponseForm(
                kakaoUserInfoResponse.getEmail(),
                kakaoUserInfoResponse.getNickname(),
                temporaryUserToken,
                kakaoUserInfoResponse.getIsNewUser()
        );
    }
}
