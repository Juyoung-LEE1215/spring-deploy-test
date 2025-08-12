package com.example.spring_juyoung.book.controller.response;

import com.example.spring_juyoung.book.service.response.ListBookResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class ListBookResponseForm {
    final private List<Map<String,Object>> bookList;
    final private Long totalItems;
    final private Integer totalPages;



    public static ListBookResponseForm from(final ListBookResponse response){
        List<Map<String,Object>> combinedbookList = response.transformToResponseForm();
        return new ListBookResponseForm(
                combinedbookList,
                response.getTotalItems(),
                response.getTotalPages()

        );
    }
}
