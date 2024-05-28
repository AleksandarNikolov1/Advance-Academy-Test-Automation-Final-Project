package com.github.tests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class GitHubApiManager extends ApiBaseTest{

    static final String REPO_EP = "https://api.github.com/user/repos";
    static final String TOKEN = "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";

    public static void createRepository(String repoName) {

        String requestBody = String.format("{\"name\": \"%s\"}", repoName);

        RestAssured
                .given()
                //        .header("Authorization", "token " + TOKEN)
                .auth()
                .oauth2(TOKEN)
                .body(requestBody)
                .when()
                .post(REPO_EP)
                .then()
                .statusCode(201);
    }


    public static void deleteRepository(String repoName){

        String baseURL = "https://api.github.com/repos/AleksandarNikolov1/";
        String deleteURL = baseURL + repoName;

        RestAssured
                .given()
//                  .header("Authorization", "token " + TOKEN)
                .auth()
                .oauth2(TOKEN)
                .when()
                .delete(deleteURL)
                .then()
                .statusCode(204);
    }
}
