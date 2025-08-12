package com.example.spring_juyoung.orders.services;

import com.example.spring_juyoung.account.entity.Account;
import com.example.spring_juyoung.account.repository.AccountRepository;
import com.example.spring_juyoung.book.entity.Book;
import com.example.spring_juyoung.orders.entity.Order;
import com.example.spring_juyoung.book.repository.BookRepository;
import com.example.spring_juyoung.orders.entity.OrderItem;
import com.example.spring_juyoung.orders.repository.AllOrderRepository;
import com.example.spring_juyoung.orders.repository.BookOrderRepository;
import com.example.spring_juyoung.orders.services.request.*;
import com.example.spring_juyoung.orders.services.response.BookOrderResponse;
import com.example.spring_juyoung.orders.services.response.CreateAllOrderResponse;
import com.example.spring_juyoung.orders.services.response.OrderListResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookOrderServiceImpl implements BookOrderService {
    private final BookRepository bookRepository;
    private final AccountRepository accountRepository;
    private final BookOrderRepository bookOrderRepository;
    private final AllOrderRepository allOrderRepository;

    @Override
    public BookOrderResponse orderBook(BookOrderRequest request) {
        Long bookId = request.getBookId();
        Optional<Book> maybeBook = bookRepository.findById(bookId);
        if (maybeBook.isEmpty()) {
            return null;
        }
        Book requestedBook = maybeBook.get();

        Long accountId = request.getAccountId();
        Optional<Account> maybeAccount = accountRepository.findById(accountId);
        if (maybeAccount.isEmpty()) {
            return null;
        }
        Account requestedAccount = maybeAccount.get();

        Order requestedOrders = request.toOrder(requestedBook, requestedAccount);
        Order savedOrders = bookOrderRepository.save(requestedOrders);

        return BookOrderResponse.from(savedOrders);
    }


    @Transactional
    @Override
    public CreateAllOrderResponse createAll(
            CreateAllOrderRequest orderRequest,
            CreateAllOrderItemRequest orderItemRequest) {

        // 1. 계정 조회
        Long accountId = orderRequest.getAccountId();
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // 2. 주문 생성 및 저장
        Order order = orderRequest.toOrder(account);
        bookOrderRepository.save(order);

        // 3. 요청된 bookId 리스트 추출
        List<Long> bookIdList = orderItemRequest.getOrderItems().stream()
                .map(CreateOrderItemRequest::getBookId)
                .distinct()
                .collect(Collectors.toList());

        // 4. Book 리스트 조회 및 검증
        //findAll 하는 일은 동일하지만 조건이 추가한 형식이라 보면 됨
        List<Book> bookList = bookRepository.findAllById(bookIdList);
        if (bookList.size() != bookIdList.size()) {
            throw new IllegalArgumentException("Book count not match");
        }

        // 5. 주문 항목 리스트 생성 및 저장
        // OrderItem 리스트를 저장하기 위해 save가 아닌 saveAll로 bulk 연산함(다수 데이터 처리할 떄 사용)
        // for문 을 돌면서 save 하는 것과 saveAll의 차이는 결국 데이터 무결성을 보장하기 위함
        List<OrderItem> orderItems = orderItemRequest.toOrderItemsList(bookList, order);
        List<OrderItem> savedOrderItems = allOrderRepository.saveAll(orderItems);

        // 6. 응답 객체 생성 후 반환
        return CreateAllOrderResponse.from(order, savedOrderItems);
    }

    @Override
    public OrderListResponse list(OrderListRequest request) {
        Pageable pageable = PageRequest.of(
                request.getPage() - 1,
                request.getPerPage(),
                Sort.by("created").descending());

        Long accountId = request.getAccountId();
        Page<Order> pageOrder = bookOrderRepository.findAllByAccountId(accountId,pageable);
        List<Order> pageOrderList = pageOrder.getContent();
        List<OrderItem> pageOrderItemList = allOrderRepository.findByOrderIn(pageOrderList);

        return OrderListResponse.from(
                pageOrderList,
                pageOrderItemList,
                pageOrder.getTotalPages(),
                pageOrder.getTotalElements()

        );
    }
}

