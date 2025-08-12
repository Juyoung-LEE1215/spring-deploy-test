package com.example.spring_juyoung.company.service.response;

import com.example.spring_juyoung.company.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterCompanyResponse {
    final private String companyName;
    final private String CEO;
    final private String companyAddress;
    final private Integer phoneNumber;
    final private String companyType;
    final private Integer companySales;

    public static RegisterCompanyResponse from(Company savedCompany) {
        return new RegisterCompanyResponse(
                savedCompany.getCompanyName(),
                savedCompany.getCEO(),
                savedCompany.getCompanyAddress(),
                savedCompany.getCompanyNumber(),
                savedCompany.getCompanyType(),
                savedCompany.getCompanySales()
        );
    }
}
