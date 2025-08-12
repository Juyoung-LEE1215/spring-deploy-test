package com.example.spring_juyoung.company.controller;

import com.example.spring_juyoung.company.controller.request_form.RegisterCompanyRequestForm;
import com.example.spring_juyoung.company.controller.response_form.RegisterCompanyResponseForm;
import com.example.spring_juyoung.company.service.CompanyService;
import com.example.spring_juyoung.company.service.request.RegisterCompanyRequest;
import com.example.spring_juyoung.company.service.response.RegisterCompanyResponse;
import com.example.spring_juyoung.redis_cache.RedisCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
@Slf4j
public class CompanyController {
    @Autowired
    RedisCacheService redisCacheService;
   private CompanyService companyService;

    @PostMapping("/register")
    public RegisterCompanyResponseForm responseForm(@RequestHeader("Authorization")String code,
                                                    @RequestBody RegisterCompanyRequestForm requestForm){

        String userToken = code.replace("Bearer ", "");
        Long accountId = redisCacheService.getValueByKey(userToken,Long.class);


        RegisterCompanyRequest request = requestForm.toRegisterCompany(accountId);
        RegisterCompanyResponse response = companyService.register(request);
        return RegisterCompanyResponseForm.from(response);


    }
}
