package BDD.StepDefinitions;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import org.apache.commons.collections4.MapUtils;
import org.junit.Assert;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.util.*;

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
            Set<String> actualObjectKeys=new HashSet<>(actualObject.keySet());
            for(String actualKey: actualObjectKeys){
                if(CollectionUtils.isNotEmpty(dataObject.getIgnoreAttributes()) && dataObject.getIgnoreAttributes().contains(actualKey)){
                    actualObject.remove(actualKey);
                    ignoreAttributesInActualResponse.add(actualKey);
                }
                if(CollectionUtils.isNotEmpty(dataObject.getIgnoreValueAttributes()) && dataObject.getIgnoreValueAttributes().contains(actualKey)){
                    actualObject.addProperty(actualKey,"Replaced");
                }
            }
        }
            if(MapUtils.isNotEmpty(dataObject.getReplacedSavedProperties())){
                for(Map.Entry<String,String> entry:dataObject.getReplacedSavedProperties().entrySet()){
                    if(!ObjectUtils.isNotEmpty(expectedObject.get(entry.getKey())) && expectedObject.get(entry.getKey()).isJsonPrimitive() &&
                            expectedObject.get(entry.getKey()).getAsString().contains(entry.getValue()) && ObjectUtils.isNotEmpty(JSONFileLoader.jsonData.get(entry.getValue()))){
                        //expectedObject.add(entry.getKey(),JSONFileLoader.jsonData.get(entry.getValue()));
                    }
                }
            }

            if(dataObject.isStrictComparision()){
                if(!actualObject.keySet().equals(expectedObject.keySet())){
                    Assert.fail(expectedObject.keySet()+" is not eual to actual data >>" +actualObject.keySet());
                    return  false;
                }
            }else {
                if(!actualObject.keySet().containsAll(expectedObject.keySet())){
                    Assert.fail(expectedObject.keySet()+" is not a subset of actual data >>"+actualObject.keySet());
                    return false;
                }
            }

            for(String key:expectedObject.keySet()){
                if(dataObject.getIgnoreCaseSensitiveAttributes().contains(key) && dataObject.getIgnoreCaseSensitiveAttributes()!=null){
                    if(!areJsonPrimitiveEquals(expectedObject.get((key)),actualObject.get(key))){
                        return false;
                    }
                }else{
                    if(!areEqualsIgnoringOrder(expectedObject.get(key),actualObject.get(key),dataObject,checkInvalidData)){
                        return false;
                    }
                }
            }
            return true;
    }

}

private static boolean areJsonPrimitiveEquals(JsonElement expectedElement,JsonElement actualElement){
        if(!actualElement.isJsonPrimitive()){
            Assert.fail(expectedElement.toString()+" is not matching with "+ actualElement.getAsString());
            return false;
        }else {
            JsonPrimitive expectedJsonValue=expectedElement.getAsJsonPrimitive();
            JsonPrimitive actualJsonValue=actualElement.getAsJsonPrimitive();
            return expectedJsonValue.isString() && actualJsonValue.isString() && expectedJsonValue.getAsString().equalsIgnoreCase(actualJsonValue.getAsString());
        }
}

private static boolean compareJsonArray(JsonElement expectedElement,JsonElement actualElement,DataObject dataObject, boolean checkInvalidData){
        JsonArray expectedElementArray=expectedElement.getAsJsonArray();
        JsonArray actualElementArray=actualElement.getAsJsonArray();
        if(!checkInvalidData){
            if(dataObject.isStrictComparision()){
                if(expectedElementArray.size()!=actualElementArray.size()){
                    Assert.fail(expectedElementArray + " is not equal of "+actualElementArray);
                    return false;
                }
            }else{
                if(expectedElementArray.size() > actualElementArray.size()){
                    Assert.fail(expectedElementArray+" is not subset of "+ actualElementArray);
                    return false;
                }
            }
        }

    Iterator<JsonElement> expectedIt=expectedElementArray.iterator();
        Iterator<JsonElement> actualIt=null;
        while(expectedIt.hasNext()){
            JsonElement expectedItem=expectedIt.next();
            actualIt=actualElementArray.iterator();
            boolean foundMatch=false;
            JsonElement actualItem=null;
            while(actualIt.hasNext()){
                actualItem=actualIt.next();
                try{
                    if(areEqualsIgnoringOrder(expectedItem,actualItem,dataObject,checkInvalidData)){
                        expectedIt.remove();
                        actualIt.remove();
                        foundMatch=true;
                        break;
                    }
                }catch(Throwable t){
                    //Ignoring the exception
                }
            }
            if(!checkInvalidData){
                if(!foundMatch){
                    Assert.fail(expectedItem.toString()+" is not found in response");
                    return false;
                }
            }else {
                if(foundMatch){
                    Assert.fail(expectedItem.toString() + "invalid data is found in the response");
                    return false;
                }
            }
        }
        return true;
}

public static boolean areEqualsIgnoringOrder(final JsonElement expectedElement, final JsonElement actualElement, DataObject dataObject, boolean checkInvalidData){
        if(expectedElement.isJsonPrimitive()){
            return compareJsonPrimitive(expectedElement,actualElement);
        }
        if(expectedElement.isJsonObject()){
            return compareJsonObject(expectedElement,actualElement,dataObject,checkInvalidData);
        }
        if(expectedElement.isJsonArray()){
            return compareJsonArray(expectedElement,actualElement,dataObject,checkInvalidData);
        }
        return checkInvalidData;
}

}
