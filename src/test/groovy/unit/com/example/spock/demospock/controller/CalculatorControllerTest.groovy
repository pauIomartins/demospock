package unit.com.example.spock.demospock.controller

import com.example.spock.demospock.controller.CalculatorController
import com.example.spock.demospock.service.CalculatorService
import spock.lang.Unroll

class CalculatorControllerTest extends spock.lang.Specification {

    private CalculatorController controller

    void setup() {
        controller = new CalculatorController()
        controller.calculatorService = Mock(CalculatorService)
    }

    @Unroll
    def "must sum two beautiful numbers: #num1 + #num2 = #res"() {
        given: "an Long number"
            1 * controller.calculatorService.sum(_ as Long, _ as Long) >> {
                return res
            }
            Long number = num1

        and: "another Long number"
            Long another = num2

        when: "sum two Long numbers"
            Map result = controller.sum([ "number1": number, "number2" : another ])

        then: "we get a very nice and correct result"
            result.sum == res

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
