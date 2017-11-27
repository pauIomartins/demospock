package com.example.spock.demospock.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public Long sum(Long num1, Long num2) {
        return num1 + num2;
    }
}
