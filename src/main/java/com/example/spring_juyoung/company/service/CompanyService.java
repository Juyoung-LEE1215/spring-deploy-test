package com.example.spring_juyoung.company.service;

import com.example.spring_juyoung.company.service.request.RegisterCompanyRequest;
import com.example.spring_juyoung.company.service.response.RegisterCompanyResponse;

public interface CompanyService {


    RegisterCompanyResponse register(RegisterCompanyRequest request);
}
