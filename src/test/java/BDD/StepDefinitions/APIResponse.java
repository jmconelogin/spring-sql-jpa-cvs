package BDD.StepDefinitions;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class APIResponse {
    private String Url;
    private Response requestSpecification;
    private String responseCode;
    private String responseBody;
    private String expectedResponseBody;

    public String getResponseBodyKey() {
        return responseBodyKey;
    }

    public void setResponseBodyKey(String responseBodyKey) {
        this.responseBodyKey = responseBodyKey;
    }

    private String responseBodyKey;

    public String getExpectedResponseCode() {
        return expectedResponseCode;
    }

    public void setExpectedResponseCode(String expectedResponseCode) {
        this.expectedResponseCode = expectedResponseCode;
    }

    private String expectedResponseCode;

    public Response getRequestSpecification() {
        return requestSpecification;
    }

    public void setRequestSpecification(Response requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getExpectedResponseBody() {
        return expectedResponseBody;
    }

    public void setExpectedResponseBody(String expectedResponseBody) {
        this.expectedResponseBody = expectedResponseBody;
    }
}
