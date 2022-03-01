package BDD.StepDefinitions;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.nl.En;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;
import BDD.StepDefinitions.ApiHandler;
import org.apache.commons.lang3.StringUtils;

public class StepDeifinition  {
    private ApiHandler apiHandler;

    private static void loadFiles(){
        try{
            String filePath="C:\\Users\\c323897\\IdeaProjects\\spring-sql-jpa-cvs\\src\\test\\java\\BDD\\package.json";
            JSONFileLoader.loadFiles(filePath);
        }catch(IOException e){
            System.out.println("File Handling Exception Here");
        }
    }
    @Given("The following endpoint")
    public void given(DataTable dataTable){
        apiHandler.populateRequest(dataTable);
        apiHandler.executeAPI(dataTable);
    }
}