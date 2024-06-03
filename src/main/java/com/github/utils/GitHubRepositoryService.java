package com.github.utils;

import io.qameta.allure.Step;
import io.restassured.RestAssured;

public class GitHubRepositoryService {

    static final String REPO_EP = "https://api.github.com/user/repos";
    static final String TOKEN = "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";
    static final String USERNAME = "Test-username22";

    @Step("Precondition - create repository")
    public static void createRepository(String repoName) {

        String requestBody = String.format("{\"name\": \"%s\"}", repoName);

        RestAssured
                .given()
                .auth()
                .oauth2(TOKEN)
                .body(requestBody)
                .when()
                .post(REPO_EP)
                .then()
                .statusCode(201);
    }


    @Step("Post-condition - delete repository")
    public static void deleteRepository(String repoName) {
        String baseURL = "https://api.github.com/repos/";
        String deleteURL = baseURL + USERNAME + "/" + repoName;

        RestAssured
                .given()
                .auth()
                .oauth2(TOKEN)
                .when()
                .delete(deleteURL)
                .then()
                .statusCode(204);
    }
}
