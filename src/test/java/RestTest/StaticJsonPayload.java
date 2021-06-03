package RestTest;

import Utility.JsonParse;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;


public class StaticJsonPayload {
    @Test
    public void test_numberOfCircuits2017_season() throws IOException {

        //fetch the placeId from post api call response using static Json Payload
        //content of the file to string
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given()
                .log()
                .all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(Files.readString(Paths.get("src/main/java/Files/Payload.json")))
                .when()
                .post("maps/api/place/add/json")
                .then()
                .assertThat().
                        statusCode(200)
                .extract().response().asString();
        JsonPath jsonPath = JsonParse.JsonParsing(response);
        String status = jsonPath.getString("status");
        System.out.println(status);

    }
}
