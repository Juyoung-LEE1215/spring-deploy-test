package com.example.spring_juyoung.orders.cotroller;

import com.example.spring_juyoung.orders.cotroller.request.CreateAllOrdersRequestForm;
import com.example.spring_juyoung.orders.cotroller.request.OrderListRequestForm;
import com.example.spring_juyoung.orders.cotroller.response.CreateAllOrdersResponseForm;
import com.example.spring_juyoung.orders.cotroller.response.OrderListResponseForm;
import com.example.spring_juyoung.orders.services.BookOrderService;
import com.example.spring_juyoung.orders.services.request.CreateAllOrderItemRequest;
import com.example.spring_juyoung.orders.services.request.CreateAllOrderRequest;
import com.example.spring_juyoung.orders.services.request.OrderListRequest;
import com.example.spring_juyoung.orders.services.response.CreateAllOrderResponse;
import com.example.spring_juyoung.orders.services.response.OrderListResponse;
import com.example.spring_juyoung.redis_cache.RedisCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping({"/order", "/orderitem"})
@RequiredArgsConstructor

// 다중 주문 -> 주문 하나 재부에 여러개의 주문 항목이 배치되어 있음
// Order의 경우 이 주문이 누구의 것인지 알면됨(Account)
// 주문일자를 토대로 언제 주문이 되었고 배송이 지연되는지 여부 파악할 수도 있음.

//OrderItem -> 수량,항목(BookId),가격,어디의 주문인지(OrderId)

public class BookOrderController {
    @Autowired
    RedisCacheService redisCacheService;
    @Autowired
    BookOrderService bookOrderService;

//    @PostMapping("/create")
//    public BookOrderResponseForm orderResponseForm(@RequestHeader("Authorization") String code,
//                                                   @RequestBody BookOrderRequestForm orderRequestForm){
//        String userToken = code.replace("Bearer", "").trim();
//        Long accountId = redisCacheService.getValueByKey(userToken, Long.class);
//
//        BookOrderRequest request = orderRequestForm.toBookOrderRequest(accountId);
//        BookOrderResponse response = bookOrderService.orderBook(request);
//        return BookOrderResponseForm.from(response);
//
//    }
    @PostMapping("/create/all")
    public CreateAllOrdersResponseForm createAllOrderResponseForm(@RequestBody CreateAllOrdersRequestForm requestForm,
                                                                 @RequestHeader("Authorization") String code){
        String userToken = code.replace("Bearer", "").trim();
        Long accountId = redisCacheService.getValueByKey(userToken, Long.class);

        CreateAllOrderRequest ordersRequest = requestForm.toCreateAllOrdersRequest(accountId);
        CreateAllOrderItemRequest orderItemRequest = requestForm.toCreateAllOrderItemRequest();
        CreateAllOrderResponse response = bookOrderService.createAll(ordersRequest, orderItemRequest);

        return CreateAllOrdersResponseForm.from(response);
    }
    @GetMapping("/list")
    public OrderListResponseForm orderListResponseForm
            (@RequestHeader("Authorization") String code,
             @ModelAttribute OrderListRequestForm orderListRequestForm){

        String userToken = code.replace("Bearer", "").trim();
        Long accountId = redisCacheService.getValueByKey(userToken, Long.class);

        OrderListRequest listRequest = orderListRequestForm.toListOrder(accountId);
        OrderListResponse listResponse = bookOrderService.list(listRequest);
        return OrderListResponseForm.from(listResponse);



    }



}
