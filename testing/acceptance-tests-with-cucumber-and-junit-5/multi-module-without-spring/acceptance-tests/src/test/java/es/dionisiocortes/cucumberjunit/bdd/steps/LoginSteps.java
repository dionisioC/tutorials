package es.dionisiocortes.cucumberjunit.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LoginSteps {

    private String username;
    private Response response;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @AfterAll
    public static void tearDown() {
        RestAssured.reset();
    }

    @Given("admin user wants to login")
    public void adminUserWantsToLogin() {
        username = "admin";
    }

    @When("the user tries to login as admin")
    public void theUserTriesToLoginAsAdmin() {

        response = given().request()
                .contentType(ContentType.JSON)
                .body(Map.of("username", username, "password", "anything"))
                .when().post("/api/auth/signin");

    }

    @Then("the user is allowed to use the app.")
    public void theUserIsAllowedToUseTheApp() {

        response.then().assertThat()
                .statusCode(200)
                .body(equalTo("Come in"));

    }

    @Given("other user wants to login")
    public void otherUserWantsToLogin() {
        username = "otherUser";
    }

    @When("the user tries to login")
    public void theUserTriesToLogin() {

        response = given().request()
                .contentType(ContentType.JSON)
                .body(Map.of("username", username, "password", "anything"))
                .when().post("/api/auth/signin");
    }

    @Then("the user is not allowed to use the app.")
    public void theUserIsNotAllowedToUseTheApp() {
        response.then().assertThat()
                .statusCode(403)
                .body(equalTo("Not allowed"));
    }
}
