package tests;

import models.UserBodyLombokModel;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static specs.ReqResSpecs.*;

public class RecRes {


    @Test
    void checkFirstNameUser() {
        given()
                .spec(baseRequestSpec)
                .get("/api/users/2")
                .then()
                .spec(baseResponseSpec)
                .body("data.first_name", is("Janet"));
    }

    @Test
    void checkUnknownStatusCode() {
        given()
                .spec(baseRequestSpec)
                .get("/api/unknown")
                .then()
                .spec(baseResponseSpec)
                .statusCode(200);
    }

    @Test
    void checkNotFoundRequest() {
        given()
                .spec(baseRequestSpec)
                .get("/api/unknown/23")
                .then()
                .spec(baseResponseSpec)
                .statusCode(404);
    }

    @Test
    void checkTotalUsers() {
        given()
                .spec(baseRequestSpec)
                .get("/api/users?page=2")
                .then()
                .spec(baseResponseSpec)
                .body("total", is(12));
    }

    @Test
    void checkSupportText() {
        String textSupport = "To keep ReqRes free, contributions towards server costs are appreciated!";
        given()
                .spec(baseRequestSpec)
                .get("/api/unknown/2")
                .then()
                .spec(baseResponseSpec)
                .body("support.text", is(textSupport));
    }

    @Test
    void checkCreateUser() {
        UserBodyLombokModel user = new UserBodyLombokModel();
        user.setName("mark");
        user.setJob("QA");
        given()
                .spec(createUserRequestSpec)
                .body(user)
                .post()
                .then()
                .spec(createUserResponseSpec)
                .extract()
                .as(UserBodyLombokModel.class);
    }

    @Test
    void checkGetInformUser() {

        given()
                .spec(getUsers)
                .log().all()
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                .body("data.findAll{it.first_name}.first_name.flatten()",
                        hasItem("Tracey"));



    }

}
