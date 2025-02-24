package com.example.jinder;

import com.example.jinder.dto.SignUpDto;
import com.example.jinder.enums.Gender;
import com.example.jinder.service.UserService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@RequiredArgsConstructor
//@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
public class JinderApplicationTests {

    @Test
    @DisplayName("Успешная регистрация")
    void successRegistration() {
        var maleSignUp = new SignUpDto(
                "email@gmail.com",
                "pass",
                "nickname",
                Gender.MALE,
                "test");

        RestAssured
                .given()
                .body(maleSignUp)
                .contentType(JSON)
                .log().all()
                .post("/registration/sign-up")
                .then()
                .log().all()
                .statusCode(HttpStatus.CREATED.value())
                .contentType(ContentType.TEXT);
    }
}
