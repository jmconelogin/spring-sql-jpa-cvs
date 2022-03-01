package BDD.StepDefinitions;

import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.JsonElement;
import gherkin.deps.com.google.gson.reflect.TypeToken;
import gherkin.deps.com.google.gson.stream.JsonReader;
import net.bytebuddy.description.method.MethodDescription;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JSONFileLoader {
    public static Map<String, JsonElement> jsonData= new ConcurrentHashMap<>();

    public static void loadFiles(String path) throws FileNotFoundException {
         Type mapType=new TypeToken<Map<String,JsonElement>>() {}.getType();
        File directory=new File(path);
        directoryTransversal(directory,mapType);
    }
    public static void directoryTransversal(File directory,Type mapType) throws FileNotFoundException {
        File[] flist=directory.listFiles();
        for(File file:flist) {
            if(file.isFile()){
                JsonReader reader=new JsonReader(new FileReader(file));
                jsonData.putAll(new Gson().fromJson(reader,mapType));
            }else if(file.isDirectory()){
                directoryTransversal(file,mapType);
            }
        }
    }
}
