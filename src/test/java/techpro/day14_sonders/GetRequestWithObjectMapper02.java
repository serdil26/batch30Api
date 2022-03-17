package techpro.day14_sonders;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import techpro.testBase.HerOkuAppTestBase;
import techpro.utilities.JsonUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetRequestWithObjectMapper02 extends HerOkuAppTestBase {
     /*
    GetRequestWithObjectMapper02:
  https://restful-booker.herokuapp.com/booking/2 url’ine bir get request gönderildiğinde,
 status kodun 200 ve response body’nin
{
"firstname": "Susan",
"lastname": "Smith",
"totalprice": 401,
"depositpaid": true,
"bookingdates": {
"checkin": "2015-12-16",
"checkout": "2017-03-17"
},
"additionalneeds": "Breakfast"
}
Olduğunu Object Mapper kullanarak test edin
     */
    @Test
    public void test(){
        spec02.pathParams("para1","booking","para2",2);
        //DATAYI STRING OLARAK ATA
        String jsonData="{\n" +
                "\"firstname\": \"Susan\",\n" +
                "\"lastname\": \"Smith\",\n" +
                "\"totalprice\": 232,\n" +
                "\"depositpaid\": true,\n" +
                "\"bookingdates\": {\n" +
                "\"checkin\": \"2015-12-16\",\n" +
                "\"checkout\": \"2017-03-17\"\n" +
                "},\n" +
                "\"additionalneeds\": \"Breakfast\"\n" +
                "}";
        //EXPECTED DATA OLUSTUR
        HashMap<String, Object>expectedData= JsonUtil.convertJsonToJava(jsonData, HashMap.class);
        System.out.println("expected data= "+expectedData);
        //REQUEST GONDER
        Response response= given().spec(spec02).when().get("/{para1}/{para2}");
        response.prettyPrint();

        //ACTUAL DATA OLUSTUR
        HashMap<String, Object> actualData=JsonUtil.convertJsonToJava(response.asString(),HashMap.class);
        System.out.println("actual data= "+ actualData);

        //ASSERTION YAP
        Assert.assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        Assert.assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        Assert.assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        Assert.assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        Assert.assertEquals(expectedData.get("bookingdates.checkin"), actualData.get("bookingdates.checkin"));
        Assert.assertEquals(expectedData.get("bookingdates.checkout"), actualData.get("bookingdates.checkout"));
        Assert.assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));


    }
}
