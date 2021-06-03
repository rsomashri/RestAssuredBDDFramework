package RestTest;

import Utility.Payload;
import io.restassured.RestAssured;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class DynamicJson {
    //Adding the book in library by using post method
    @Test(dataProvider = "bookData")
    public void addBook(String isbn,String aisle){
        RestAssured.baseURI="http://216.10.245.166";
        String response=given().header("Content-Type", "application/json").
                body(Payload.addBook(isbn,aisle)).
                when().post("Library/Addbook.php").then().extract().response().asString();
        System.out.println(response);
    }

    //deleting the same books from library otherwise we will get book already exist
    @Test(dataProvider = "bookData")
    public void deleteBook(String isbn,String aisle){
        RestAssured.baseURI="http://216.10.245.166";
        String response=given().header("Content-Type", "application/json").
                body(Payload.deleteBook(isbn+aisle)).
                when().delete("Library/DeleteBook.php").then().extract().response().asString();
        System.out.println(response);
    }
//parameterizing adding and deleting book by using dataProvider
// both the methods will run 3 times with two elements.

    @DataProvider(name="bookData")
    public Object[][] getdata(){
    return new Object[][]{
            {"java","2278"},{"selenium","789"},{"rft","67890"}
    };

    }
}
