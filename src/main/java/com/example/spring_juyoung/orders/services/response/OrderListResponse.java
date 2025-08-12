package com.example.spring_juyoung.orders.services.response;

import com.example.spring_juyoung.orders.cotroller.response.OrderListResponseForm;
import com.example.spring_juyoung.orders.entity.Order;
import com.example.spring_juyoung.orders.entity.OrderItem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class OrderListResponse {
    final private List<Order> pageOrderList;
    final private List<OrderItem> pageOrderItemList;
    final private Integer totalPages;
    final private Long totalElements;

  public OrderListResponseForm transformToResponseForm() {
      List<Map<String,Object>> orderList = pageOrderList.stream()
              .map(order -> {
                  Map<String,Object> map = new HashMap<>();
                  map.put("orderId", order.getId());
                  map.put("created", order.getCreated());

                  List<OrderItem> itemForOrder = pageOrderItemList.stream()
                          .filter(orderItem -> orderItem.getOrder().getId().equals(order.getId()))
                          .collect(Collectors.toList());

                  String bookorderlist = itemForOrder.stream()
                          .map(item->item.getBook().getTitle())
                          .collect(Collectors.joining(","));
                  if(bookorderlist.length()>20){
                      bookorderlist = bookorderlist.substring(0,20) + ".....";
                  }
                  map.put("orderName",bookorderlist);

                  Long totalPrice = itemForOrder.stream()
                          .mapToLong(item -> item.getPrice() * item.getQuantity())
                          .sum();
                  map.put("totalPrice",totalPrice);
                  return map;
              })
              .collect(Collectors.toList());
      return new OrderListResponseForm(
              orderList,
              totalPages,
              totalElements
      );

  }
    public static OrderListResponse from(
            final List<Order> pagedOrdersList,
            final List<OrderItem> pagedOrderItemList,
            final Integer totalPages,
            final Long totalElements) {

        return new OrderListResponse(
                pagedOrdersList,
                pagedOrderItemList,
                totalPages,
                totalElements
        );
    }


}
