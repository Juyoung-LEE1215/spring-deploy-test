package com.example.spring_juyoung.company.controller.response_form;

import com.example.spring_juyoung.company.service.response.RegisterCompanyResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterCompanyResponseForm {
    private String companyName;
    private String CEO;
    private String content;
    private String companyAddress;
    private Integer phoneNumber;
    private Integer companyNumber;
    private String companyType;
    private Integer companySales;

    public RegisterCompanyResponseForm(String companyName, String ceo, String companyAddress, Integer phoneNumber, String companyType, Integer companySales) {
    }

    public static RegisterCompanyResponseForm from(RegisterCompanyResponse response) {
        return new RegisterCompanyResponseForm(
                response.getCompanyName(),
                response.getCEO(), 
                response.getCompanyAddress(),
                response.getPhoneNumber(),
                response.getCompanyType(),
                response.getCompanySales()
        );
    }
}
