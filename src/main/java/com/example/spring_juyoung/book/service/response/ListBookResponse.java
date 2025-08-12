package com.example.spring_juyoung.book.service.response;

import com.example.spring_juyoung.book.entity.Book;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class ListBookResponse {
    final private List<Book> bookList;
    final private Long totalItems;
    final private Integer totalPages;

    public List<Map<String,Object>> transformToResponseForm(){
        return bookList.stream()
                .map(book -> {
                    Map<String, Object> bookmap = new HashMap<>();
                    bookmap.put("id", book.getId());
                    bookmap.put("title", book.getTitle());
                    bookmap.put("price", book.getPrice());
                    bookmap.put("nickname", book.getAccount().getNickname());

                    return bookmap;
                })
                .collect(Collectors.toList());
    }
    public static ListBookResponse from(Page<Book> paginatedBook) {
        return new ListBookResponse(
                paginatedBook.getContent(),
                paginatedBook.getTotalElements(),
                paginatedBook.getTotalPages()
        );
    }

}
