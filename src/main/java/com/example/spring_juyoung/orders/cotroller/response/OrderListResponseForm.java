package com.example.spring_juyoung.orders.cotroller.response;

import com.example.spring_juyoung.orders.services.response.OrderListResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class OrderListResponseForm {
   final private  List<Map<String,Object>> orderList;
    final private Integer totalPages;
    final private Long totalElements;


    public static OrderListResponseForm from(OrderListResponse listResponse) {
        return listResponse.transformToResponseForm();
    }
}
