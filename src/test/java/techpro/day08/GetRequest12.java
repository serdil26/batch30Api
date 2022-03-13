package techpro.day08;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import techpro.testBase.HerOkuAppTestBase;
import techpro.testData.HerOkuTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest12 extends HerOkuAppTestBase {
      /*
    https://restful-booker.herokuapp.com/booking/1 url ine bir istek gönderildiğinde
 dönen response body nin
{
 "firstname": "Jim",
 "lastname": "Smith",
 "totalprice": 939,
 "depositpaid": false,
 "bookingdates": {
 "checkin": "2016-09-09",
 "checkout": "2017-09-21"
 }
 } gibi olduğunu test edin
     */

    @Test
    public void test(){
        //url olustur
        spec02.pathParams("para1", "booking", "para2", 1);

        //expected data olustur
        HerOkuTestData expectedObjesi= new HerOkuTestData();
        HashMap<String, Object> expectedData= expectedObjesi.setupTestData();

        //request gonder
        Response response= given().spec(spec02).when().get("/{para1}/{para2}");
        response.prettyPrint();


        //DE-SERIALIZATION ILE ASSERT
        HashMap<String, Object> actualData= response.as(HashMap.class);

        Assert.assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        Assert.assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        Assert.assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        Assert.assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        Assert.assertEquals(((Map)expectedData.get("bookingdates")).get("checkin"),
                ((Map)actualData.get("bookingdates")).get("checkin"));
        Assert.assertEquals(((Map<?, ?>) expectedData.get("bookingdates")).get("checkout"),
                ((Map<?, ?>) actualData.get("bookingdates")).get("checkout"));

        //JSON PATH ILE ASSERTION

        JsonPath jsonPath= response.jsonPath();

        Assert.assertEquals(expectedData.get("firstname"), jsonPath.getString("firstname"));
        Assert.assertEquals(expectedData.get("lastname"), jsonPath.getString("lastname"));
        Assert.assertEquals(expectedData.get("totalprice"), jsonPath.getInt("totalprice"));
        Assert.assertEquals(expectedData.get("depositpaid"), jsonPath.getBoolean("depositpaid"));
        Assert.assertEquals(((Map)expectedData.get("bookingdates")).get("checkin"), jsonPath.getString("bookingdates.checkin"));
        Assert.assertEquals(((Map)expectedData.get("bookingdates")).get("checkout"), jsonPath.getString("bookingdates.checkout"));


    }



}
