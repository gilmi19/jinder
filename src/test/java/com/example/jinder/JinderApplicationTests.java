package com.example.jinder;

import com.example.jinder.dto.AuthDto;
import com.example.jinder.dto.SignInDto;
import com.example.jinder.dto.SignUpDto;
import com.example.jinder.entity.Token;
import com.example.jinder.enums.Gender;
import com.example.jinder.repository.SessionRepository;
import com.example.jinder.repository.TokenRepository;
import com.example.jinder.repository.UserRepository;
import com.example.jinder.service.AuthenticationService;
import com.example.jinder.service.AuthorizationService;
import com.example.jinder.service.TokenService;
import com.example.jinder.service.UserService;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.Commit;

import java.util.Optional;

import static com.example.jinder.enums.Gender.FEMALE;
import static com.example.jinder.enums.Gender.MALE;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.TEXT;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = DEFINED_PORT)
@AutoConfigureMockMvc
@Commit
@Slf4j
public class JinderApplicationTests {
    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private CleanUpServiceTest cleanUpServiceTest;

    private static final String NICKNAME = "nickname";
    private static final String FEMALE_EMAIL = "testfemale@gmail.com";
    private static final String MALE_EMAIL = "testmale@gmail.com";

    @BeforeEach
    void init() {
        cleanUpServiceTest.cleanUp(NICKNAME);
        checkServiceAndRepositoryInjection();
    }

    @Test
    @DisplayName("Успешная регистрация")
    void successRegistration() {
        createUser();
    }

    @Test
    @DisplayName("Успешное удаление токена")
    void successTokenDelete() {
        createUser();
        tokenRepository.deleteByUserJinder_Nickname(NICKNAME);
        Optional<Token> optionalToken = tokenRepository.findByUserJinder_Nickname(NICKNAME);
        assertTrue(optionalToken.isEmpty());
    }

    @Test
    @DisplayName("Успешная аутентификация")
    void successAuthentication() {
        authentication();
    }

    @Test
    @DisplayName("Сброс токена")
    void successResetToken() {
        createUser();
        RestAssured.given()
                .queryParam("email", "email@gmail.com")
                .log().all()
                .post("/auth/reset-token")
                .then()
                .log().all()
                .statusCode(OK.value());
    }

    @Test
    @DisplayName("Успешная авторизация")
    void successAuthorization() {
        authorization();
    }

    @Test
    @DisplayName("Успешный выход из сессии")
    void successSignOut() {
        authorization();

        String token = sessionRepository.findByUserJinder_Nickname(NICKNAME).orElseThrow().getToken();
        RestAssured.given()
                .header(new Header("sessionToken", token))
                .contentType(JSON)
                .log().all()
                .post("/sign-out")
                .then()
                .log().all()
                .statusCode(OK.value())
                .contentType(TEXT);
    }

    @Test
    @DisplayName("Успешный лайк")
    void likeSuccess() {
        log.info("Создаем пользователей");
        createUserByParam(MALE_EMAIL, "pass", "Amir", MALE, "descr");
        createUserByParam(FEMALE_EMAIL, "pass", "Inna", FEMALE, "perdit");

        log.info("Находим токен по имени пользователя");
        String amirToken = createAuthTokenByNickname("Amir");
        String innaToken = createAuthTokenByNickname("Inna");

        log.info("Создаем AuthDTO");
        var amirAuthDto = new AuthDto(amirToken, MALE_EMAIL, "pass");
        var innaAuthDto = new AuthDto(innaToken, FEMALE_EMAIL, "pass");

        log.info("Проводим аутентификацию");
        authenticationByAuthDto(amirAuthDto);
        authenticationByAuthDto(innaAuthDto);

        var amirSignInDto = new SignInDto("Amir", "pass");
        var innaSignInDto = new SignInDto("Inna", "pass");

        log.info("Входим в сессию");
        signInByDto(amirSignInDto);
        signInByDto(innaSignInDto);

        log.info("Получаем токен по сесиии");
        String amirSessionToken = getSessionTokenByNickname("Amir");
        log.info("amirSessionToken: {}", amirSessionToken);
        String innaSessionToken = getSessionTokenByNickname("Inna");

        log.info("Показываем пользователей");
        showRequest(amirSessionToken, "Inna", "perdit");
        showRequest(innaSessionToken, "Amir", "descr");

        log.info("Ставим лайки");
        likeRequest("Inna", false, amirSessionToken);
        likeRequest("Amir", true, innaSessionToken);
    }

    private void authorization() {
        String token = createUserAndGetToken(NICKNAME);
        authenticationService
                .authenticate(new AuthDto(token, "email@gmail.com", "pass"));

        var body = new SignInDto(NICKNAME, "pass");
        RestAssured.given()
                .body(body)
                .contentType(JSON)
                .log().all()
                .post("/sign-in")
                .then()
                .log().all()
                .statusCode(OK.value());
    }

    private String createUserAndGetToken(String nickname) {
        createUser();
        return tokenRepository.findByUserJinder_Nickname(nickname).orElseThrow().getToken();
    }

    private void createUser() {
        userRepository.deleteByNickname(NICKNAME);
        var maleSignUp = new SignUpDto(
                "email@gmail.com",
                "pass",
                NICKNAME,
                MALE,
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
                .contentType(TEXT);
    }

    private void createUserByParam(String email,
                                   String password,
                                   String nickname,
                                   Gender gender,
                                   String description) {
        userRepository.deleteByNickname(NICKNAME);
        var signUpDto = new SignUpDto(
                email,
                password,
                nickname,
                gender,
                description);

        RestAssured
                .given()
                .body(signUpDto)
                .contentType(JSON)
                .log().all()
                .post("/registration/sign-up")
                .then()
                .log().all()
                .statusCode(HttpStatus.CREATED.value())
                .contentType(TEXT);
    }

    private void authentication() {
        String authToken = createUserAndGetToken(NICKNAME);
        var authDto = new AuthDto(authToken, "email@gmail.com", "pass");
        RestAssured
                .given()
                .body(authDto)
                .contentType(JSON)
                .log().all()
                .post("/auth")
                .then()
                .log().all()
                .statusCode(OK.value());
    }

    private String createAuthTokenByNickname(String nickname) {
        return tokenRepository.findByUserJinder_Nickname(nickname).orElseThrow().getToken();
    }

    private void authenticationByAuthDto(AuthDto dto) {
        log.info("Authentication, {}, {}, {}", dto.email(), dto.password(), dto.token());
        RestAssured
                .given()
                .body(dto)
                .contentType(JSON)
                .log().all()
                .post("/auth")
                .then()
                .log().all()
                .statusCode(OK.value());
    }

    private void signInByDto(SignInDto dto) {
        log.info("signIn, user param -  {}, {}", dto.nickname(), dto.password());
        RestAssured.given()
                .body(dto)
                .contentType(JSON)
                .log().all()
                .post("/sign-in")
                .then()
                .log().all()
                .statusCode(OK.value());
    }

    private String getSessionTokenByNickname(String nickname) {
      //  return tokenRepository.findByUserJinder_Nickname(nickname).orElseThrow().getToken();
        return sessionRepository.findByUserJinder_Nickname(nickname).orElseThrow().getToken();
    }

    private void showRequest(String sessionToken, String expectNickname, String expectDescription) {
        log.info("sessionToken is {}", sessionToken);
        RestAssured.given()
                .header(new Header("sessionToken", sessionToken))
                .contentType(JSON)
                .log().all()
                .post("/api/show")
                .then()
                .log().ifValidationFails()
                .body("nickname", Matchers.equalTo(expectNickname))
                .body("description", Matchers.equalTo(expectDescription))
                .statusCode(OK.value());
    }

    private void likeRequest(String nickname, boolean isSecondLike, String sessionToken) {
        log.info("Like request");
        RestAssured.given()
                .queryParam("nickname", nickname)
                .queryParam("isSecondUser", isSecondLike)
                .header(new Header("sessionToken", sessionToken))
                .log().all()
                .post("/api/like")
                .then()
                .statusCode(OK.value());
    }

    private void checkServiceAndRepositoryInjection() {
        assertNotNull(userService, "userService не внедрён!");
        assertNotNull(tokenService, "tokenService не внедрён!");
        assertNotNull(tokenRepository, "tokenRepository не внедрён!");
        assertNotNull(userRepository, "userRepository не внедрён!");
        assertNotNull(authenticationService, "authenticationService не внедрён!");
        assertNotNull(authorizationService, "authorizationService не внедрён!");
        assertNotNull(sessionRepository, "sessionRepository не внедрён!");
    }
}
