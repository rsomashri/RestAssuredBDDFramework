package RestTest;


import Utility.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetBDD {



    @Test
    public void test_numberOfCircuits2017_season() {
//given()------all input details
//when()---------- submit the api --resource,http method
//then()-----validate the response
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response=given()
                .log()
                .all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(payload.loadPayload())
                .when()
                .post("maps/api/place/add/json")
                .then()
                .assertThat().
                statusCode(200)
                .body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
        System.out.println(response);
        JsonPath jsonPath=new JsonPath(response);
        String placeId=jsonPath.getString("place_id");
        System.out.println(placeId);

    }




}
