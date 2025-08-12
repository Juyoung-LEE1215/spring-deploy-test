package com.example.spring_juyoung.cart.service;

import com.example.spring_juyoung.cart.service.request.CartRequest;
import com.example.spring_juyoung.cart.service.response.CartResponse;

public interface CartService {
    CartResponse bookCart(CartRequest request);

}
