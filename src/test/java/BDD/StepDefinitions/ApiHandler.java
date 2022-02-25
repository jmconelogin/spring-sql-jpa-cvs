package BDD.StepDefinitions;

import cucumber.api.DataTable;

import java.util.List;
import java.util.Map;

public class ApiHandler {
    public void populateRequest(DataTable dataTable){
        List<Map<String,String>> dataList=dataTable.asMaps(String.class,String.class);

    }

    public void executeAPI(){

    }
}