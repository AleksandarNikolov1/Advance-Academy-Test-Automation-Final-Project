package com.github.tests;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import org.testng.annotations.BeforeMethod;

public class ApiBaseTest {
    @BeforeMethod
    public void setup(){
      //  RestAssured.baseURI = "https://reqres.in/api";
        RestAssured.config = RestAssuredConfig.config()
                .logConfig(LogConfig.logConfig()
                        .enableLoggingOfRequestAndResponseIfValidationFails());
    }
}
