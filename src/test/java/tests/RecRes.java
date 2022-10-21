package tests;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class RecRes {


    @Test
    void checkFirstNameUser() {
        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().body()
                .body("data.first_name", is("Janet"));
    }

    @Test
    void checkUnknownStatusCode() {
        given()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    @Test
    void checkNotFoundRequest() {
        given()
                .when()
                .get("https://reqres.in/api/unknown/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    void checkTotalUsers() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .body("total", is(12));
    }

    @Test
    void checkSupportText() {
        String textSupport = "To keep ReqRes free, contributions towards server costs are appreciated!";
        given()
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .log().status()
                .log().body()
                .body("support.text", is(textSupport));
    }
}
