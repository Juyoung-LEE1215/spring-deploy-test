package com.example.spring_juyoung.kakao_authentication.cotroller;


import com.example.spring_juyoung.kakao_authentication.cotroller.response_form.KakaoLoginResponseForm;
import com.example.spring_juyoung.kakao_authentication.service.KakaoAuthenticationService;
import com.example.spring_juyoung.kakao_authentication.cotroller.response_form.KakaoUserInfoResponseForm;
import com.example.spring_juyoung.kakao_authentication.service.response.KakaoUserInfoResponse;
import com.example.spring_juyoung.redis_cache.RedisCacheService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.time.Duration;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/kakao-authentication")
public class KakaoAuthenticationController {

    final private KakaoAuthenticationService kakaoAuthenticationService;
    final private RedisCacheService redisCacheService;

    @GetMapping("/login")
    public KakaoLoginResponseForm requestLogin(@RequestParam("code") String code) throws IOException {

        log.info("requestLogin(): code {}", code);

        KakaoUserInfoResponse response = kakaoAuthenticationService.handleLogin(code);
        String accessToken = response.getAccessToken();
        String temporaryUserToken = createTemporaryUserToken(accessToken);

        return KakaoLoginResponseForm.from(response, temporaryUserToken);
    }
    private String createTemporaryUserToken(String accessToken) {
        try{
            String temporaryUserToken = UUID.randomUUID().toString();
            redisCacheService.setKeyAndValue(temporaryUserToken, accessToken, Duration.ofMinutes(5));
            return temporaryUserToken;
        }catch (Exception e){
            throw new RuntimeException("Redis code ERROR " + e.getMessage());
        }
    }
}
