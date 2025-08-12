package com.example.spring_juyoung.cart.service;

import com.example.spring_juyoung.account.entity.Account;
import com.example.spring_juyoung.account.repository.AccountRepository;
import com.example.spring_juyoung.book.entity.Book;
import com.example.spring_juyoung.book.repository.BookRepository;
import com.example.spring_juyoung.cart.entity.Cart;
import com.example.spring_juyoung.cart.repository.CartRepository;
import com.example.spring_juyoung.cart.service.request.CartRequest;
import com.example.spring_juyoung.cart.service.response.CartResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final BookRepository bookRepository;
    private final AccountRepository accountRepository;
    private final CartRepository cartRepository;

    public CartServiceImpl(BookRepository bookRepository, AccountRepository accountRepository, CartRepository cartRepository) {
        this.bookRepository = bookRepository;
        this.accountRepository = accountRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public CartResponse bookCart(CartRequest request) {
        Long bookId = request.getBookId();
        Optional<Book> maybeBook = bookRepository.findById(bookId);
        if (maybeBook.isEmpty()) {
            return null;
        }
        Book cartBook = maybeBook.get();

        Long accountId = request.getAccountId();
        Optional<Account> maybeaccount = accountRepository.findById(accountId);
        if (maybeaccount.isEmpty()) {
            return null;
        }
        Account cartAccount = maybeaccount.get();

        Cart requestedCart = request.toCart(cartBook, cartAccount);
        Cart savedCart = cartRepository.save(requestedCart);

        return CartResponse.from(savedCart);
    }
}
