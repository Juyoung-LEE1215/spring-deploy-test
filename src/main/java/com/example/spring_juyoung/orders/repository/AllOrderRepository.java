package com.example.spring_juyoung.orders.repository;

import com.example.spring_juyoung.orders.entity.Order;
import com.example.spring_juyoung.orders.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AllOrderRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderIn(List<Order> pageOrderList);
}
