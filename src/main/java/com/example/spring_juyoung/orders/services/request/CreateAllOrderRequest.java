package com.example.spring_juyoung.orders.services.request;

import com.example.spring_juyoung.account.entity.Account;
import com.example.spring_juyoung.orders.entity.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateAllOrderRequest {
    private final Long accountId;


    public Order toOrder(Account account) {
        return new Order(account);
    }
}
