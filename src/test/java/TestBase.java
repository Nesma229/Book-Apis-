import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class TestBase {

    public String token(){

        return "7c0f3788dbd28c8d7a1e78859ae327d56e136fe7085b0fa72144b0e88569e0a5";

    }

@BeforeSuite
    public void setUp(){
       RestAssured.baseURI = "https://simple-books-api.glitch.me";

    }

}
