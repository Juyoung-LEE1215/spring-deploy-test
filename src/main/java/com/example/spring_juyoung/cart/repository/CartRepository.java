package com.example.spring_juyoung.cart.repository;

import com.example.spring_juyoung.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
