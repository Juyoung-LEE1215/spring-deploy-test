package com.example.spring_juyoung.cart.controller;


import com.example.spring_juyoung.cart.controller.request.RequestForm;
import com.example.spring_juyoung.cart.controller.response.ResponseForm;
import com.example.spring_juyoung.cart.service.CartService;
import com.example.spring_juyoung.cart.service.request.CartRequest;
import com.example.spring_juyoung.cart.service.response.CartResponse;
import com.example.spring_juyoung.redis_cache.RedisCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor

public class CartController {
    @Autowired
    RedisCacheService redisCacheService;
    @Autowired
    CartService cartService;

    @PostMapping("/create")
    public ResponseForm responseForm(@RequestBody RequestForm requestForm,
                                     @RequestHeader ("Authorization") String token){
        String userToken = token.replace("Bearer ", "").trim();
        Long accountId = redisCacheService.getValueByKey(userToken, Long.class);

        CartRequest request = requestForm.toRequestCart(accountId);
        CartResponse response= cartService.bookCart(request);
        return ResponseForm.from(response);
    }


}
