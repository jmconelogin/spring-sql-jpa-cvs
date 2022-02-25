package BDD.StepDefinitions;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.nl.En;

import java.lang.annotation.Annotation;
import java.util.List;
import BDD.StepDefinitions.ApiHandler;

public class StepDeifinition  {
    private ApiHandler apiHandler;
    @Given("The following endpoint")
    public void given(DataTable dataTable){
        apiHandler.populateRequest(dataTable);
        apiHandler.executeAPI();
    }
}