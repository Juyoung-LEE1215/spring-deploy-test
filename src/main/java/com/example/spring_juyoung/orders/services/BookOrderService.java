package com.example.spring_juyoung.orders.services;

import com.example.spring_juyoung.orders.services.request.BookOrderRequest;
import com.example.spring_juyoung.orders.services.request.CreateAllOrderItemRequest;
import com.example.spring_juyoung.orders.services.request.CreateAllOrderRequest;
import com.example.spring_juyoung.orders.services.request.OrderListRequest;
import com.example.spring_juyoung.orders.services.response.BookOrderResponse;
import com.example.spring_juyoung.orders.services.response.CreateAllOrderResponse;
import com.example.spring_juyoung.orders.services.response.OrderListResponse;

public interface BookOrderService {

    BookOrderResponse orderBook(BookOrderRequest request);


    CreateAllOrderResponse createAll(
            CreateAllOrderRequest orderRequest,
            CreateAllOrderItemRequest orderItemRequest);

    OrderListResponse list(OrderListRequest request);
}
