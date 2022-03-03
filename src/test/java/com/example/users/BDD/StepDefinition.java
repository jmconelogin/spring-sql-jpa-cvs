package com.example.users.BDD;

import com.example.users.UsersApplication;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("test")
@ContextConfiguration(classes = UsersApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class StepDefinition  {

    private static final Logger logger = LogManager.getLogger(StepDefinition.class);

    @LocalServerPort
    private int port;


    Response response;

/*    @Given("User wants to publish prescription")
    public void user_wants_to_publish_prescription(){

}*/
    @When("a post request is made to \\/demo\\/addPrescription")
    public void aPostRequestIsMadeToDemoAddPrescription() {

        BasicConfigurator.configure();
        logger.info("Testing Here: ");
        Map<String,String> queryParams=new HashMap<>();
        queryParams.put("name","Test");
        queryParams.put("email","testing@email.com");
        response = RestAssured.given().contentType("application/json").queryParams(queryParams)
                .when().post("http://localhost:" + 8080 + "/demo/addPrescription").then().extract().response();
        System.out.println(response.statusCode());
    }
@Then("the response will be saved")
    public void the_response_will_be_saved(){
        Assert.assertEquals("SAVED",response.statusLine());
}
@And("I should get back a status code of 200")
    public void status_is_success(){
    Assert.assertEquals(200,response.statusCode());
}

}
