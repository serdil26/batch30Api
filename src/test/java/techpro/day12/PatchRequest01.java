package techpro.day12;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import techpro.testBase.JsonPlaceHolderTestBase;
import techpro.testData.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PatchRequest01 extends JsonPlaceHolderTestBase {
    /*
    https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönderdiğimde
   {
      "title": "API calismaliyim"
     }
Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
 "userId": 10,
 "title": "API calismaliyim"
 "completed": true,
 "id": 198
}
     */

    @Test
    public void test(){
        //url olustur
        spec01.pathParams("para1", "todos", "para2", 198);

        //expected ve request data olustur
        JsonPlaceHolderTestData testData=new JsonPlaceHolderTestData();
        JSONObject requestData= testData.setupPatchRequestData();
        JSONObject expectedData=testData.setupPatchExpectedData();

        //request gonder
        Response response= given().contentType(ContentType.JSON).
                spec(spec01).auth().basic("admin", "password123").
                body(requestData.toString()).when().patch("/{para1}/{para2}");
        response.prettyPrint();

        //jsonpath ile
        JsonPath jsonPath=response.jsonPath();
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(expectedData.get("userId"), jsonPath.getInt("userId"));
        Assert.assertEquals(expectedData.get("title"), jsonPath.getString("title"));
        Assert.assertEquals(expectedData.get("completed"), jsonPath.getBoolean("completed"));
        Assert.assertEquals(expectedData.get("id"), jsonPath.getInt("id"));

        //deserialization ile
        HashMap<String, Object> actualData=response.as(HashMap.class);
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(expectedData.get("userId"), actualData.get("userId"));
        Assert.assertEquals(expectedData.get("title"), actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"), actualData.get("completed"));
        Assert.assertEquals(expectedData.get("id"), actualData.get("id"));





    }
}
