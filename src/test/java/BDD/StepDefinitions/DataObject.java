package BDD.StepDefinitions;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataObject {
    List<String> getIgnoreAttributes;
    List<String> getGetIgnoreValueAttributes;
    Map<String,String> getReplacedSavedProperties;
    public List<String> getIgnoreAttributes() {
        return null;
    }

    public List<String> getIgnoreValueAttributes() {
        return null;
    }

    public Map<String,String> getReplacedSavedProperties() {
        return null;
    }

    public boolean isStrictComparision() {
        return true;
    }

    public List<String> getIgnoreCaseSensitiveAttributes() {
        return null;
    }
}
