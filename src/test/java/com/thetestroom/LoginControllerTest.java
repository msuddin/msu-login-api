package com.thetestroom;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginControllerTest {

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    // [REF 1.0]
    // Absolute basic example of using Rest Assured for GET http method
    @Test
    public void shouldGetHello() {
        given()
            .get("hello")
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body(is("Hello, I am awake"));
    }

    // [REF 2.0]
    // Example of testing GET http method that expects URL path variables
    @Test
    public void shouldBeAbleToLoginUsingValidCredentials() {
        given()
            .get("login/admin/password")
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body(is("hello admin/password"));
    }

    // [REF 2.0]
    // Example of testing GET http method that expects URL path variables
    @Test
    public void shouldBeAbleToLoginUsingValidCredentialsUsingParams() {
        given()
            .pathParam("username", "admin")
            .pathParam("password", "password")
            .request("get","login/{username}/{password}")
        .then()
            .body(is("hello admin/password"));
    }

    // [REF 3.0]
    // In a GET http method, we pass in all the variables that are needed for the constructor of the object
    // The object is then returned as a JSON
    @Test
    public void shouldBeAbleToGenerateLoginSessionBasedOnParameterRequest() {
        given()
            .contentType(ContentType.JSON)
            .queryParam("userName", "admin")
            .queryParam("password", "password")
        .when()
            .request("get", "loginName")
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body("userName", is("admin"))
            .body("password", is("password"));
    }

    // [REF 4.0]
    // POST http method in which we pass in an object
    // And the object is then returned
    @Test
    public void shouldBeAbleToGenerateLoginSessionBasedOnRequestBody() {
        given()
            .contentType(ContentType.JSON)
            .body(new LoginSession("admin", "password"))
        .when()
            .post("login")
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body("userName", is("admin"))
            .body("password", is("password"));
    }

}
