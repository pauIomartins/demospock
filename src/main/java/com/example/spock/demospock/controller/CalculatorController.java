package com.example.spock.demospock.controller;

import com.example.spock.demospock.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @RequestMapping(method = RequestMethod.POST, path = "/sum")
    public Map sum(@RequestBody Map<String, Long> numbers) {
        Map result = new HashMap<>();
        result.put("sum", calculatorService.sum(numbers.get("number1"), numbers.get("number2")));
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hello")
    public String helloJsus() {
        return "Hello Jesus";
    }
 }
