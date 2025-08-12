package com.example.spring_juyoung.orders.repository;

import com.example.spring_juyoung.account.entity.Account;
import com.example.spring_juyoung.orders.entity.Order;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookOrderRepository extends JpaRepository<Order, Long> {

    List<Order> account(Account account);
    
    // @Query는 CustomQuery를 구성하는 것으로 JPQL 이라고 한다.
    // ManyToOne의 Lazy Fetch를 하기 떄문에 join fetch를 통해 'N -> 1' 문제에 대응한다.
    // 안하면 서비스 장애로 이어지게 된다.
    // countQuery는 페이지네이션에 처리를 위해 전체 개수를 조회하는 역할을 수행
    // join fetch가 count 연산과 호완되지 않기 떄문에 별도로 구성해야 한다.
    // countQuery가 없으면 제대로 최적화가 진행되지 않으므로 성능이 떨어지게 됨
    
    @Query(
            value = "select o from Order o join fetch o.account a where a.account_id = :accountId",
            countQuery = "select count(o) from Order o where o.account.account_id= :accountId"
    )

    Page<Order> findAllByAccountId(@Param("accountId")Long accountId, Pageable pageable);
}
