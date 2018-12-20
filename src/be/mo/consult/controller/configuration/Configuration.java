package be.mo.consult.controller.configuration;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Configuration {

    private JsonElement jsonElement;

    public Configuration(JsonElement jsonElement) {
        this.jsonElement = jsonElement;
    }

    public JsonElement get(){
        return jsonElement;
    }

    public JsonElement get(String path){
        JsonElement jsonElement = this.jsonElement;
        JsonElement response = null;
        String delimiter = "\\.";
        ArrayList<String> keys = new ArrayList<String>(Arrays.asList(path.split(delimiter)));
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            if(!jsonElement.isJsonNull() && jsonElement.isJsonObject()){
                jsonElement = jsonElement.getAsJsonObject().get(key);
                response = jsonElement;
            }
        }
        return response;
    }
}
