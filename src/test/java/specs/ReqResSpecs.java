package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;

import static helpers.CustomApiListener.withCustomTemplates;
import static org.hamcrest.Matchers.notNullValue;

public class ReqResSpecs {

    public static RequestSpecification baseRequestSpec = with()
            .filter(withCustomTemplates())
            .baseUri("https://reqres.in")
            .log().uri()
            .log().body()
            .contentType(ContentType.JSON);

    public static RequestSpecification createUserRequestSpec = with()
            .filter(withCustomTemplates())
            .baseUri("https://reqres.in")
            .basePath("/api/users")
            .log().uri()
            .log().body()
            .contentType(ContentType.JSON);

    public static ResponseSpecification createUserResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectBody("id", notNullValue())
            .build();

    public static ResponseSpecification baseResponseSpec = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();


    public static RequestSpecification getUsers = with()
            .filter(withCustomTemplates())
            .baseUri("https://reqres.in")
            .basePath("/api/users?page")
            .log().uri()
            .log().body()
            .contentType(ContentType.JSON);
}
