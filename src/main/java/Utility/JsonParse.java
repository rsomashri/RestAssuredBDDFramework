package Utility;
import io.restassured.path.json.JsonPath;
public class JsonParse {

    public static JsonPath JsonParsing(String response) {
        JsonPath jsonPath = new JsonPath(response);
        return jsonPath;
    }
}
