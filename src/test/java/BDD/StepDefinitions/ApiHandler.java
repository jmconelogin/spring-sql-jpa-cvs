package BDD.StepDefinitions;

import cucumber.api.DataTable;
import gherkin.deps.com.google.gson.JsonElement;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import com.google.gson.JsonParser;


import java.util.List;
import java.util.Map;

public class ApiHandler {

    public void populateRequest(DataTable dataTable){
        List<Map<String,String>> dataList=dataTable.asMaps(String.class,String.class);


    }

    public Response executeAPIMethod(RequestSpecification request, APIDataObject apiDataObject){
        switch(apiDataObject.getApiRequest().getUrl()){
            case "GET":
                return request.get(apiDataObject.getApiRequest().getUrl());
            case "POST":
                if(StringUtils.isNotEmpty(apiDataObject.getApiRequest().getRequestBody())){
                    request.body(apiDataObject.getApiRequest().getRequestBody());
                }
                return request.post(apiDataObject.getApiRequest().getUrl());
            case "PATCH":
                if(StringUtils.isNotEmpty(apiDataObject.getApiRequest().getRequestBody())){
                    request.body(apiDataObject.getApiRequest().getRequestBody());
                }
                return request.patch(apiDataObject.getApiRequest().getUrl());
            case "PUT":
                if(StringUtils.isNotEmpty(apiDataObject.getApiRequest().getRequestBody())){
                    request.body(apiDataObject.getApiRequest().getRequestBody());
                }
                return request.put(apiDataObject.getApiRequest().getUrl());
            case "DELETE":
                return request.delete(apiDataObject.getApiRequest().getUrl());
            default:
                return null;
        }
    }

    public void executeAPI(APIDataObject apiDataObject){
        RestAssured.baseURI="";
        RequestSpecification requestSpecification=RestAssured.given();
        EncoderConfig encoderConfig=new EncoderConfig();
        requestSpecification.config(RestAssured.config().encoderConfig(encoderConfig.appendDefaultContentCharsetToContentTypeIfUndefined(false)));
        requestSpecification.contentType(apiDataObject.getApiRequest().getContentType());
        if(ObjectUtils.isNotEmpty(apiDataObject.getApiRequest().getHeaders())){
            requestSpecification.header(apiDataObject.getApiRequest().getHeaders());
        }

        apiDataObject.getApiRequest().setRequestSpecification(requestSpecification);
        apiDataObject.getApiResponse().setRequestSpecification(executeAPIMethod(requestSpecification,apiDataObject));
        apiDataObject.getApiResponse().setResponseCode(String.valueOf(apiDataObject.getApiResponse().getRequestSpecification().getStatusCode()));
        apiDataObject.getApiResponse().setResponseBody(apiDataObject.getApiResponse().getRequestSpecification().body().asString());
    }

    public void parseExpectedResponse(APIDataObject apiDataObject){
        try{
            Assert.assertEquals(apiDataObject.getApiResponse().getExpectedResponseCode(),apiDataObject.getApiResponse().getResponseCode());
        }catch (Throwable t){
            System.out.println("ResponseCode assertion failed, Actual Response: "+apiDataObject.getApiResponse().getResponseBody()+" Expected Response : "+apiDataObject.getApiResponse().getExpectedResponseBody());
            throw t;
        }
        if(StringUtils.isNotBlank(apiDataObject.getApiResponse().getResponseBodyKey())){
            try{
                Assert.assertTrue(JsonComparision.areEqualsIgnoringOrder(apiDataObject.getApiResponse().getExpectedResponseBody(),
                        JsonParser.parseString(apiDataObject.getApiResponse().getResponseBody()),apiDataObject,false));
            }catch (Throwable t){
                System.out.println("ResponseCode assertion failed, Actual Response: "+apiDataObject.getApiResponse().getResponseBody()+" Expected Response : "+apiDataObject.getApiResponse().getExpectedResponseBody());
                throw t;
            }
        }else {
            System.out.println("Response Body is not found, Check the entire dataobject: "+apiDataObject);
        }
        if(StringUtils.isNotBlank(apiDataObject.getInvalidDataKey())){
            try{
                Assert.assertTrue(JsonComparision.areEqualsIgnoringOrder(apiDataObject.getInvalidDataKey(),JsonParser.parseString(apiDataObject.getApiResponse().getResponseBody()),
                        apiDataObject,true));
            }catch (Throwable t){
                throw t;
            }
        }
    }

    private APIResponse populateAPIResponse(Map<String, String> data){
        APIResponse response=new APIResponse();
        response.setExpectedResponseCode(data.get("RESPONSE_CODE"));
        JsonElement expectedElement=null;
        if(ObjectUtils.isNotEmpty(data.get("RESPONSE_BODY_KEY"))){
            response.setResponseBodyKey(data.get("RESPONSE_BODY_KEY"));
            expectedElement=JSONFileLoader.jsonData.get(response.getResponseBodyKey());
        }
        response.setExpectedResponseBody(expectedElement.getAsString());
        return response;
    }
}