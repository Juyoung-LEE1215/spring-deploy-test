package com.example.spring_juyoung.orders.services.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderListRequest {
    final private Integer page;
    final private Integer perPage;
    final private Long accountId;
}
