package techpro.day05;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest04 {
     /*
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 200
    ve content type'inin "application/json"
    ve firstname'in “Jim"
    ve totalprice’in 600
    ve checkin date'in 2015-06-12"oldugunu test edin
    */
    @Test
    public void test01(){
        String url= "https://restful-booker.herokuapp.com/booking/5";
        Response response= given().when().get(url);

        response.prettyPrint();

        response.then().assertThat().
                statusCode(200).
                contentType("application/json").
                body("firstname", equalTo("Mark"),
                        "totalprice",equalTo(326),
                        "bookingdates.checkin", equalTo("2017-05-03"));


    }
}
