package RestTest;

import Pojo.AddPlace;
import Pojo.Location;

import io.restassured.RestAssured;

import org.testng.annotations.Test;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SerializeTest {
    @Test
    public void test_numberOfCircuits2017_season() throws IOException {
        //fetch the placeId from post api call response using static Json Payload
        //content of the file to string
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        AddPlace addPlace = new AddPlace();
        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        List<String> types = new ArrayList<>();
        addPlace.setAccuracy("50");
        addPlace.setAddress("29, side layout, cohen 09");
        addPlace.setLanguage("Bengali");
        addPlace.setName("Baker Street");
        addPlace.setPhone_number("090");
        addPlace.setWebsite("sherlock.com");
        types.add("shop");
        types.add("shoe park");
        addPlace.setTypes(types);
        addPlace.setLocation(location);
        String response = given()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(addPlace)
                .when()
                .post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);


    }
}
