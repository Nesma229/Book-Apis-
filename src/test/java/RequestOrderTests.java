import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class RequestOrderTests extends TestBase{




    @Test
    public  void postOrder(){
        File body = new File("src/test/resources/OrderData.json");

             Response getIDFromRes =    given().basePath("/orders")
                .header("Authorization",token())
                .contentType(ContentType.JSON)
                .body(body)
                .when().post()
                .then()
                .log().all()
                .statusCode(201)
                .extract().response();
           String OrderId = getIDFromRes.path("orderId");
        System.out.println("ID is  : " + OrderId);

    }


    @Test
    public void getOrders() throws IOException {
        Response res =  given().basePath("/orders").
                header("Authorization",token()).
                accept(ContentType.JSON). //Response is accepted as Json
                when().get()
                .then().extract().response();


        //  .statusCode(200).body("size()",equalTo(3));

        System.out.println(res.asString());
        FileWriter file = new FileWriter("./output.json");
        file.write(res.prettyPrint());
        file.flush();
        file.close();
    }



@Test
    public void updateOrder(){
    HashMap<String,String> request = new HashMap();
    request.put("customerName","nesma");
    given()
            . basePath("/orders/ID9uoslSpAAlTMYvMIe9Y")
            .header("Authorization",token())
            .contentType(ContentType.JSON)
            .body(request)
            .when()
            .patch()
            .then()
            .log().all()
            .statusCode(204);
}
    @Test
    public void getOrderByID(){
        given()
                .basePath(" /orders/ID9uoslSpAAlTMYvMIe9Y")
                .header("Authorization",token())
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .log().all()
                .statusCode(200);
        //.assertThat().body("create", equalTo("true"));
    }

    @Test
    public void deletOrder(){
        given()
                .basePath(" /orders/ID9uoslSpAAlTMYvMIe9Y")
                .header("Authorization",token())
                .contentType(ContentType.JSON)
                .when().delete()
                .then()
                .log().all()
                .statusCode(204);
    }

    @Test
    public void deletUnavilableOrder(){
        given()
                .basePath(" /orders/ID9uoslSpAAlTMYvMIe9Y")
                .header("Authorization",token())
                .contentType(ContentType.JSON)
                .when().delete()
                .then()
                .log().all()
                .statusCode(404)
                .body("error", equalTo("No order with id ID9uoslSpAAlTMYvMIe9Y."));


    }

    //    @Test
//    public void RegClientToGetToken(){
//        HashMap<String,String> clientData = new HashMap();
//        clientData.put("clientName","nada");
//        clientData.put( "clientEmail" , "nada@gmail.com");
//
//       Response getToken =  given().basePath("/api-clients/")
//                .body(clientData)
//                .when()
//                .post()
//                .then()
//               .log().all()
//               .extract().response();
//       String token = getToken.path("token");
//
//        System.out.println("token is " + token);
//
//    }

}
