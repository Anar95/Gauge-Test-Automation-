package utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import netscape.javascript.JSObject;

import java.io.FileReader;

public class LocatorReader {

    private  static  final String LOCATOR_PATH = "src/test/resources/locator/";



    public static String getLocatorType(String elementName) throws Exception{
        JsonObject json = JsonParser.parseReader(new FileReader(LOCATOR_PATH)).getAsJsonObject();
        JsonObject element = json.getAsJsonObject(elementName);
        return element.get("locatorType").getAsString();

    }

    public static String getLocatorValue(String elementName) throws Exception{
        JsonObject json = JsonParser.parseReader(new FileReader(LOCATOR_PATH)).getAsJsonObject();
        JsonObject element = json.getAsJsonObject(elementName);
        return element.get("locatorValue").getAsString();

    }

}
