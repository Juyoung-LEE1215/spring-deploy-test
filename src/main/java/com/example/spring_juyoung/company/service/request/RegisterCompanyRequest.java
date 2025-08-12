package com.example.spring_juyoung.company.service.request;

import com.example.spring_juyoung.account.entity.Account;
import com.example.spring_juyoung.company.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterCompanyRequest {
    final private String companyName;
    final private String CEO;
    final private String companyAddress;
    final private Integer phoneNumber;
    final private String companyType;
    final private Integer companySales;

    public Company toCompanyRegister(Account account) {
        return new Company(companyName,CEO,companyAddress,phoneNumber,companyType,companySales,account);
    }

    public Long getAccountId() {

    }
}
