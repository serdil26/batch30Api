package techpro.day12;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;
import techpro.testBase.DummyTestBase;
import techpro.testData.DummyTestData;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteRequest01 extends DummyTestBase {
     /*
    http://dummy.restapiexample.com/api/v1/delete/2 bir DELETE request gönderdiğimde
 Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
 {
 "status": "success",
 "data": "2",
 "message": "Successfully! Record has been deleted"
 }
     */
    @Test
    public void test(){
        spec03.pathParams("para1","delete", "para2", 2);

        DummyTestData testData=new DummyTestData();
        JSONObject expectedData= testData.setupDeleteData();

        Response response= given().contentType(ContentType.JSON).
                spec(spec03).auth().basic("admin","password123").
                when().delete("/{para1}/{para2}");
        response.prettyPrint();

        //Matchers class ile assertion
        response.then().assertThat().statusCode(200).
                body("status", equalTo(expectedData.getString("status")),
                        "data",equalTo(expectedData.getString("data")),
                        "message",equalTo(expectedData.getString("message")));

    }
}
