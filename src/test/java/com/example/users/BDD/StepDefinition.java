package com.example.users.BDD;

import com.lob.example.ExampleApplication;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("test")
@ContextConfiguration(classes = ExampleApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class StepDefinition  {

    private static final Logger logger = LogManager.getLogger(StepDefinition.class);

    @LocalServerPort
    private int port;


    Response response;

    Map<String,String> parameters=new HashMap<>();

    @Given("The user provides username {string} and email {string}")
    public void user_wants_to_publish_prescription(String userName,String email){
   parameters.put("name",userName);
        parameters.put("email",email);
}
    @When("a post request is made to {string} {string}")
    public void aPostRequestIsMadeToDemoAddPrescription(String basePath, String endPoint) {

        BasicConfigurator.configure();
        logger.info("Testing Here: "+parameters);
        response = RestAssured.given().contentType("application/json").queryParams(parameters)
                .when().post("http://localhost:" + 8080 + "/"+basePath+"/"+endPoint).then().extract().response();
        System.out.println("StatusCode here: "+response.body().prettyPrint());
    }
@Then("the response will be saved")
    public void the_response_will_be_saved(){
        Assert.assertEquals("Saved",response.body().prettyPrint());
}
@And("I should get back a status code of 200")
    public void status_is_success(){
    Assert.assertEquals(HttpStatus.SC_OK,response.statusCode());
}


}
