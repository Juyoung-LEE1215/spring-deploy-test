package com.example.spring_juyoung.book.service;

import com.example.spring_juyoung.account.entity.Account;
import com.example.spring_juyoung.account.repository.AccountRepository;
import com.example.spring_juyoung.book.entity.Book;
import com.example.spring_juyoung.book.repository.BookRepository;
import com.example.spring_juyoung.book.service.request.ListBookRequest;
import com.example.spring_juyoung.book.service.request.RegisterBookRequest;
import com.example.spring_juyoung.book.service.response.ListBookResponse;
import com.example.spring_juyoung.book.service.response.RegisterBookResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final AccountRepository accountRepository;
    private final BookRepository bookRepository;

    @Override
    public RegisterBookResponse register(RegisterBookRequest request, Long accountId) {
        Optional<Account> maybeAccount = accountRepository.findById(accountId);
        if(maybeAccount.isEmpty()) {
            log.error("Account not found");
            return null;
        }
        Account account = maybeAccount.get();
        Book requestedBook = request.toBook(account);
        Book createdBook = bookRepository.save(requestedBook);
        return RegisterBookResponse.from(createdBook);
    }

    @Override
    public ListBookResponse list(ListBookRequest request) {
        //PageRequest 생성(시작은 0부터, 화면에 보여줄 떈 이상하지만 1이사러 -1을 해야한다)
        PageRequest pageRequest = PageRequest.of(
                request.getPage()-1,
                request.getPerPage());

        Page<Book> paginatedBook = bookRepository.findAll(pageRequest);
        return ListBookResponse.from(paginatedBook);
    }
}
