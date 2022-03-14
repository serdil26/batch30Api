package techpro.day10;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import techpro.testBase.HerOkuAppTestBase;
import techpro.testData.HerOkuTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostRequest02 extends HerOkuAppTestBase {
     /*
    https://restful-booker.herokuapp.com/booking
    { "firstname": "Selim",
               "lastname": "Ak",
               "totalprice": 11111,
               "depositpaid": true,
               "bookingdates": {
                   "checkin": "2020-09-09",
                   "checkout": "2020-09-21"
                }
 }
 gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
 "booking": {
         "firstname": " Selim ",
         "lastname": " Ak ",
         "totalprice":  11111,
         "depositpaid": true,
         "bookingdates": {
             "checkin": "2020-09-01",
              "checkout": " 2020-09-21”
         },
        }
olduğunu test edin
     */

    @Test
    public void test(){
        //url olustur
        spec02.pathParam("para1", "booking");

        //request body ve expected data ayni oldugu icin tek bir json objec kullanilmasi yeterlidir
        HerOkuTestData testData=new HerOkuTestData();
        JSONObject expectedRequestData= testData.setupTestAndRequestData();
        System.out.println("json objesi= " + expectedRequestData);

        //request gonder
        Response response=given().
                contentType(ContentType.JSON).
                spec(spec02).
                auth().basic("admin","password123").
                body(expectedRequestData.toString()).
                when().post("/{para1}");

         response.prettyPrint();

         //DESERIALIZATION ILE ASSERT
        HashMap<String, Object > actualDataMap= response.as(HashMap.class);
        System.out.println("actual data= "+ actualDataMap);

        Assert.assertEquals(expectedRequestData.get("firstname"), ((Map)actualDataMap.get("booking")).get("firstname"));
        Assert.assertEquals(expectedRequestData.get("lastname"), ((Map)actualDataMap.get("booking")).get("lastname"));
        Assert.assertEquals(expectedRequestData.getInt("totalprice"),
                ((Map<?, ?>) actualDataMap.get("booking")).get("totalprice"));

        Assert.assertEquals(expectedRequestData.getBoolean("depositpaid"),
                ((Map<?, ?>) actualDataMap.get("booking")).get("depositpaid"));

        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin"),
                ((Map)((Map<?, ?>) actualDataMap.get("booking")).get("bookingdates")).get("checkin"));

        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout"),
                ((Map)((Map<?, ?>) actualDataMap.get("booking")).get("bookingdates")).get("checkout"));

        //JsonPath Yöntemi

        JsonPath json=response.jsonPath();
        Assert.assertEquals(expectedRequestData.getString("lastname"),
                json.getString("booking.lastname"));

        Assert.assertEquals(expectedRequestData.getString("firstname"),
                json.getString("booking.firstname"));

        Assert.assertEquals(expectedRequestData.getInt("totalprice"),
                json.getInt("booking.totalprice"));

        Assert.assertEquals(expectedRequestData.getBoolean("depositpaid"),
                json.getBoolean("booking.depositpaid"));
        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin"),
                json.getString("booking.bookingdates.checkin"));

        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout"),
                json.getString("booking.bookingdates.checkout"));
    }
}
