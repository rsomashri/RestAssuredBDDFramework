package RestTest;

import static io.restassured.RestAssured.*;

import Pojo.GetCourse;
import Pojo.Api;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import org.testng.annotations.Test;

import java.util.List;


public class TestOAuthRestAssured {

    public static String clientId = "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com";
    public static String redirectUri = "https://rahulshettyacademy.com/getCourse.php";
    public static String grant_type = "authorization_code";
    public static String client_secret = "erZOWM9g3UtwNRj340YYaK_W";
    public static String code = "4%2F0AX4XfWh8zkqFrNvNWdzYIlM8VPv0ZXdEacOtGRwEkl0F6GWGajeF4uwCvZfJ_16RXszeRQ";

    @Test
    public static void getAccessToken() {
        String response = given()
                .urlEncodingEnabled(false)
                .queryParams("code", code)
                .queryParam("client_id", clientId)
                .queryParams("client_secret", client_secret)
                .queryParams("grant_type", grant_type)
                .queryParams("redirect_uri", redirectUri)
                .when().log().all()
                .post("https://www.googleapis.com/oauth2/v4/token").asString();

        JsonPath jsonPath = new JsonPath(response);
        String accessToken = jsonPath.getString("access_token");
        TestOAuthRestAssured.getActualRequestResponse(accessToken);
    }

    public static void getActualRequestResponse(String accessToken) {
        GetCourse actualResponse = given().contentType("application/json").
                queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)
                .when()
                .get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
        System.out.println(actualResponse.getExpertise());
        System.out.println(actualResponse.getCourses().getApi().get(1).getCourseTitle());
        System.out.println(actualResponse.getLinkedIn());
        List<Api> apiCourses=actualResponse.getCourses().getApi();
        for(int i=0; i < apiCourses.size(); i++){
        if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI webservices Testing"))
            {
                System.out.println(apiCourses.get(i).getPrice());
            }
            System.out.println(apiCourses.get(i).getCourseTitle());
        }

    }

}

