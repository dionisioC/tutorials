package es.dionisiocortes.cucumberjunit.bdd.steps;

import es.dionisiocortes.cucumberjunit.bdd.CucumberSpringConfiguration;
import es.dionisiocortes.cucumberjunit.controller.LoginDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@CucumberContextConfiguration
public class LoginSteps extends CucumberSpringConfiguration {

    private String username;
    private ResponseEntity<String> response;
    @Given("admin user wants to login")
    public void adminUserWantsToLogin() {
        username = "admin";
    }

    @When("the user tries to login as admin")
    public void theUserTriesToLoginAsAdmin() {
        response = testRestTemplate.postForEntity(
                "/api/auth/signin", new LoginDto(username, "anything"), String.class);
    }

    @Then("the user is allowed to use the app.")
    public void theUserIsAllowedToUseTheApp() {
        Assertions.assertEquals("Come in", response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Given("other user wants to login")
    public void otherUserWantsToLogin() {
        username = "otherUser";
    }

    @When("the user tries to login")
    public void theUserTriesToLogin() {
        response = testRestTemplate.postForEntity(
                "/api/auth/signin", new LoginDto(username, "anything"), String.class);
    }

    @Then("the user is not allowed to use the app.")
    public void theUserIsNotAllowedToUseTheApp() {
        Assertions.assertEquals("Not allowed", response.getBody());
        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }
}
