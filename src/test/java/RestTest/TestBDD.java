package RestTest;


import Utility.JsonParse;
import Utility.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestBDD {

    @Test
    public void test_numberOfCircuits2017_season() {
//given()------all input details
//when()---------- submit the api --resource,http method
//then()-----validate the response

        //fetch the placeId from post api call response, Update the same place Id in put call.
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response=given()
                .log()
                .all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(Payload.loadPayload())
                .when()
                .post("maps/api/place/add/json")
                .then()
                .assertThat().
                statusCode(200)
                .body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
        JsonPath jsonPath=JsonParse.JsonParsing(response);
        String placeId=jsonPath.getString("place_id");

        String newAddress="70 Summer walk, USA";
        //put call
        given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(Payload.loadPayload(placeId,newAddress))
                .when().put("maps/api/place/update/json").then()
                .assertThat().
                statusCode(200);


        //get call to validate the same placeID is updated for the location
        String getResponse=given().log().all().queryParam("key", "qaclick123").queryParam("place_id",placeId)
                .header("Content-Type", "application/json")
                .body(Payload.loadPayload(placeId,newAddress))
                .when().get("/maps/api/place/get/json").then()
                .assertThat().
                statusCode(200).extract().response().asString();
        JsonPath jsPath=JsonParse.JsonParsing(getResponse);
            String Address=jsPath.getString("address");
        Assert.assertEquals(Address,newAddress);

    }




}
