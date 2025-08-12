package com.example.spring_juyoung.company.service;

import com.example.spring_juyoung.account.entity.Account;
import com.example.spring_juyoung.account.repository.AccountRepository;
import com.example.spring_juyoung.company.entity.Company;
import com.example.spring_juyoung.company.repository.CompanyRepository;
import com.example.spring_juyoung.company.service.request.RegisterCompanyRequest;
import com.example.spring_juyoung.company.service.response.RegisterCompanyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final AccountRepository accountRepository;

    @Override
    public RegisterCompanyResponse register(RegisterCompanyRequest request) {
        Long accountId = request.getAccountId();
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        Company requsetCompany = request.toCompanyRegister(account);
        Company savedCompany = companyRepository.save(requsetCompany);
        return RegisterCompanyResponse.from(savedCompany);

    }

}
