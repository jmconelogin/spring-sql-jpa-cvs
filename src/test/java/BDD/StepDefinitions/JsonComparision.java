package BDD.StepDefinitions;

import gherkin.deps.com.google.gson.JsonElement;
import gherkin.deps.com.google.gson.JsonObject;
import org.junit.Assert;
import org.apache.commons.collections4.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

public class JsonComparision {
    private static Set<String> ignoreAttributesInActualResponse;

    private JsonComparision(){

    }

    public static Set<String> getIgnoreAttributesInActualResponse(){
        return ignoreAttributesInActualResponse;
    }
    public static void setIgnoreAttributesInActualResponse(Set<String> ignoreAttributesInActualResponse){
        JsonComparision.ignoreAttributesInActualResponse=ignoreAttributesInActualResponse;
    }
    public static boolean compareJsonPrimitive(JsonElement expectedElement, JsonElement actualElement)
    {
        if(!actualElement.isJsonPrimitive()){
            Assert.fail(expectedElement.toString()+ "is not matching with" + actualElement.toString());
            return false;
        }else {
            return expectedElement.getAsJsonPrimitive().equals(actualElement.getAsJsonPrimitive());
        }
    }

    private static boolean compareJsonObject(JsonElement expectedElement,JsonElement actualElement,DataObject dataObject,boolean checkInvalidData ){
        if(!actualElement.isJsonObject()){
            return false;
        }else {
            JsonObject expectedObject=expectedElement.getAsJsonObject();
            JsonObject actualObject=actualElement.getAsJsonObject();

        if(CollectionUtils.isNotEmpty(dataObject.getIgnoreAttributes())||CollectionUtils.isNotEmpty(dataObject.getIgnoreValueAttributes())){
            Set<String> actualObjectKeys=new HashSet<>(actualObject.entrySet());
            for(String actualKey: actualObjectKeys){
                //if(CollectionUtils.isNotEmpty(dataObject.getIgnoreValueAttributes())) &&
            }
        }
    }
}
