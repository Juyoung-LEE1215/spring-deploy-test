package com.example.spring_juyoung.account.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "social_account")
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_id;

    private String email;
    private String nickname;


    public Account( String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

}
