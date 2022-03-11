package techpro.day05;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;  //BASA STATIC VE SONA * EKLEYEREK TUM MATCHERS KUTUPHANESI IMPORT EDILMIS OLUR
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class GetRequest03 {
    /*
    https://restful-booker.herokuapp.com/booking/7 url'ine
accept type'i "application/json" olan GET request'i yolladigimda
gelen response'un
status kodunun 200
ve content type'inin "application/json"
ve firstname'in "Mark"
ve lastname'in "Brown"
ve checkin date'in 2016-04-24"
ve checkout date'in 2017-09-10 oldugunu test edin
     */
    @Test
    public  void test01(){
        String url= "https://restful-booker.herokuapp.com/booking/7";

        Response response= given().when().get(url);
        response.prettyPrint();

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);
       response.then().
               body("firstname", equalTo("Sally")).
               body("lastname", equalTo("Brown")).
               body("totalprice", equalTo(810)).
               body("bookingdates.checkin", equalTo("2020-06-13")).
               body("bookingdates.checkout", equalTo("2020-11-11"));

       response.then().assertThat().
               statusCode(200).
               contentType(ContentType.JSON).
               body("firstname",equalTo("Sally"),
                       "lastname", equalTo("Brown"),
                       "bookingdates.checkin", equalTo("2020-06-13"),
                       "bookingdates.checkout",equalTo("2020-11-11"));



    }

}
