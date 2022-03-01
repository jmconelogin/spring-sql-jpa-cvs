package BDD.StepDefinitions;

public class APIDataObject {
    APIRequest apiRequest;
    APIResponse apiResponse;

    public String getInvalidDataKey() {
        return invalidDataKey;
    }

    public void setInvalidDataKey(String invalidDataKey) {
        this.invalidDataKey = invalidDataKey;
    }

    private String invalidDataKey;

    public APIRequest getApiRequest() {
        return apiRequest;
    }

    public void setApiRequest(APIRequest apiRequest) {
        this.apiRequest = apiRequest;
    }

    public APIResponse getApiResponse() {
        return apiResponse;
    }

    public void setApiResponse(APIResponse apiResponse) {
        this.apiResponse = apiResponse;
    }
}
