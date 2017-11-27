package com.example.spock.demospock.service

import com.example.spock.demospock.service.CalculatorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Unroll

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE

@SpringBootTest(webEnvironment = NONE)
class CalculatorServiceTest extends spock.lang.Specification {

    @Autowired
    private CalculatorService service

    void setup() {
        service = new CalculatorService()
    }

    @Unroll
    def "must sum two beautiful numbers: #num1 + #num2 = #res"() {
        given: "an Long number"
            Long number = num1

        and: "another Long number"
            Long another = num2

        when: "sum two Long numbers"
            Long result = service.sum(number, another)

        then: "we get a very nice and correct result"
            result == res

        where:
            num1    | num2    || res
            10      | 10      || 20
            15      | 10      || 25
            27      | 1       || 28
            78      | 33      || 111
            12      | 478     || 490
            1       | 1       || 2
            1234    | 4321    || 5555
    }

}
