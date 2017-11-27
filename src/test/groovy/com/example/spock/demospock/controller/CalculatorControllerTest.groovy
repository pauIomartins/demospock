package com.example.spock.demospock.controller

import groovy.json.JsonSlurper
import io.restassured.specification.RequestSpecification
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static io.restassured.RestAssured.given
import static io.restassured.http.ContentType.JSON
import static javax.servlet.http.HttpServletResponse.SC_OK

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CalculatorControllerTest extends Specification {

    @Shared
    RequestSpecification api

    @LocalServerPort
    int randomServerPort;

    def setup() {
        api = given().contentType(JSON)
    }

    def "Hello Jsus must return hello"() {
        given:
            def request = api.accept(JSON)

        when:
            def response = request.with().get("http://localhost:${randomServerPort}/hello")

        then:
            response.asString() == "Hello Jesus"
            response.statusCode == SC_OK
    }

    @Unroll
    def "Sum #num1 and #num2 must return #res"() {
        given:
            def request = api.accept(JSON)
            Map numbers = [ number1: num1, number2: num2 ]

        when:
            def response = request.with().body(numbers).post("http://localhost:${randomServerPort}/sum")

        then:
            String jsonAsString = response.asString()
            def jsonResponse = new JsonSlurper().parseText(jsonAsString)
            jsonResponse.sum == res
            response.statusCode == SC_OK

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
