package com.example.spring_juyoung.company.repository;

import com.example.spring_juyoung.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
