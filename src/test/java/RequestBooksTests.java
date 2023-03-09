import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RequestBooksTests extends TestBase{

@Test
public void getStatus() {
    given().
            basePath("/status").
            when().
            get().
            then().
            statusCode(200);
}


    @Test
    public void getBooks(){
        given().
                basePath("/books")
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200).body("size()", equalTo(6));
    }


    @Test
    public void getBookByID(){
        given().
                basePath("/books/3")
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                .body("name",equalTo( "The Vanishing Half"))
                .body("id",equalTo(3));
    }

}
