package com.example.spring_juyoung.orders.cotroller.request;

import com.example.spring_juyoung.orders.services.request.CreateAllOrderItemRequest;
import com.example.spring_juyoung.orders.services.request.CreateAllOrderRequest;
import com.example.spring_juyoung.orders.services.request.CreateOrderItemRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class CreateAllOrdersRequestForm {
    final private List<CreateOrderItemRequestForm> orderItems;

    public CreateAllOrderRequest toCreateAllOrdersRequest(Long accountId) {
        return new CreateAllOrderRequest(accountId);
    }

    public CreateAllOrderItemRequest toCreateAllOrderItemRequest() {
        //orderItem 리스트
        // 이러한 lIst 는 stream() 을 사용할 수 있음
        // map을 통해 내부에 있는 정보들을 전부 순회처리할 수 있음
        // 순회하면서 mapped에 있는 요소 하나하나는 CreateOrderItemRequest 타입이므로
        // CreateOrderItemRequest 타입들이 사용할 수 있는 toCreateOrderItemRequest() 를 통해
        // ResponseForm 타입을 Request를 변환
        // collect() 를 통해 List 타입으로 만들어줌
        List<CreateOrderItemRequest> mapped = orderItems.stream()
                .map(CreateOrderItemRequestForm::toCreateOrderItemRequest)
                .collect(Collectors.toList());
        /*
        for(int i =0; i=mapped.size(); i++){
        CreateOrderItemRequestForm mappedRequestForm = mapped.get(i);
        CreateOrderItemRequest mappedReuqest = mappedRequestForm.
         */

        return new CreateAllOrderItemRequest(mapped);
    }
}
