package RestTest;



import Utility.JsonParse;
import Utility.payload;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;


public class TestJsonResponse {
    @Test
    public void TestNestedJsonResponse() {
        int numberOfCopiesSold=0;
        String response = payload.mockResponse();
        JsonPath jsonPath = JsonParse.JsonParsing(response);
        int count = jsonPath.getInt("courses.size()");
        for(int i=0;i<count;i++)
        {
            String courses=jsonPath.get("courses["+i+"].title");
            System.out.println(courses);
            int perCourseSold=(jsonPath.getInt("courses["+i+"].price"))*(jsonPath.getInt("courses["+i+"].copies"));
            numberOfCopiesSold=perCourseSold+numberOfCopiesSold;
        }
        System.out.println(numberOfCopiesSold);
    }
}
