package techpro.day06;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import techpro.testBase.HerOkuAppTestBase;

import static io.restassured.RestAssured.given;

public class GetRequest07 extends HerOkuAppTestBase {
    /*
    https://restful-booker.herokuapp.com/booking/5 url’ine bir request yolladigimda
    HTTP Status Code’unun 200
    ve response content type’inin “application/JSON” oldugunu
    ve response body’sinin asagidaki gibi oldugunu test edin
    {"firstname": Sally,
            "lastname": "Smith",
            "totalprice": 789,
            "depositpaid": false,
            "bookingdates": {
               "checkin": "2017-12-11",
                "checkout":"2020-02-20"
                 }
    }
*/
    @Test
    public void test01(){
        spec02.pathParams("para1","booking", "para2", 5);

        Response response= given().spec(spec02).when().get("/{para1}/{para2}");
        response.prettyPrint();
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

        JsonPath json= response.jsonPath();
        Assert.assertEquals("Susan", json.getString("firstname"));
        Assert.assertEquals("Smith", json.getString("lastname"));
        Assert.assertEquals(288, json.getInt("totalprice"));
        Assert.assertEquals(true, json.getBoolean("depositpaid"));
        Assert.assertEquals("2017-03-16", json.getString("bookingdates.checkin"));
        Assert.assertEquals("2019-08-10", json.getString("bookingdates.checkout"));


    }


}
