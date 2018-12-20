package be.mo.consult.sandbox;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class _json {

    static String json = "config\\dbengine.json";

    public static void main(String[] args) throws FileNotFoundException {

        JsonReader jsonReader = new JsonReader(new InputStreamReader(new FileInputStream(json)));
        JsonElement jsonTree = new JsonParser().parse(jsonReader);
        System.out.println(jsonTree.getAsJsonObject().get("dbengine").getAsJsonObject().get("command").getAsJsonArray());
        Iterator<JsonElement> iterator = jsonTree.getAsJsonObject().get("dbengine").getAsJsonObject().get("command").getAsJsonArray().iterator();
        while (iterator.hasNext()){
            JsonElement jsonElement = iterator.next();
            System.out.println(jsonElement.getAsString());
        }
    }

}
