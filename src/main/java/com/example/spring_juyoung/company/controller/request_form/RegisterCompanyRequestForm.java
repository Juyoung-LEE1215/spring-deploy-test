package com.example.spring_juyoung.company.controller.request_form;

import com.example.spring_juyoung.company.service.request.RegisterCompanyRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterCompanyRequestForm {
    private String companyName;
    private String CEO;
    private String content;
    private String companyAddress;
    private Integer phoneNumber;
    private Integer companyNumber;
    private String companyType;
    private Integer companySales;

    public RegisterCompanyRequest toRegisterCompany(Long accountId) {
        return new RegisterCompanyRequest(
                companyName,CEO,companyAddress,phoneNumber,companyType,companySales
        );
    }
}
