package com.example.spring_juyoung.orders.cotroller.request;

import com.example.spring_juyoung.orders.services.request.OrderListRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class OrderListRequestForm {
    final private Integer page;
    final private Integer perPage;

    public OrderListRequest toListOrder(Long accountId)
    {
        return new OrderListRequest(page,perPage,accountId);
    }
}