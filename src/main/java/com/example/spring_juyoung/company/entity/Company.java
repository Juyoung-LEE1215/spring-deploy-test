package com.example.spring_juyoung.company.entity;

import com.example.spring_juyoung.account.entity.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String CEO;
    private String content;
    private String companyAddress;
    private Integer phoneNumber;
    private Integer companyNumber;
    private String companyType;
    private Integer companySales;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Company(String companyName, String ceo, String companyAddress, Integer phoneNumber, String companyType, Integer companySales, Account account) {

    }
}
