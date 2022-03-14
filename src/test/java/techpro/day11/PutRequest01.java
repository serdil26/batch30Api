package techpro.day11;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import techpro.testBase.JsonPlaceHolderTestBase;
import techpro.testData.JsonPlaceHolderTestData;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class PutRequest01 extends JsonPlaceHolderTestBase {
    /*
    https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönerdiğimde
 {
 "userId": 21,
 "title": "Wash the dishes",
 "completed": false
 }
 Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
 {
 "userId": 21,
 "title": "Wash the dishes",
 "completed": false,
 "id": 198
 }
     */
    @Test
    public void test(){
        spec01.pathParams("para1", "todos", "para2", 198);

        JsonPlaceHolderTestData testObje=new JsonPlaceHolderTestData();
        JSONObject expectedRequest= testObje.setupPutData();
        System.out.println("json object expected= " +expectedRequest);

        Response response= given().contentType(ContentType.JSON).
                spec(spec01).
                auth().basic("admin", "password123").
                body(expectedRequest.toString()).
                when().put("/{para1}/{para2}");
        response.prettyPrint();

        //JSON PATH ILE ASSERT
        JsonPath jsonPath=response.jsonPath();
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(expectedRequest.getInt("userId"),jsonPath.getInt("userId"));
        Assert.assertEquals(expectedRequest.getString("title"),jsonPath.getString("title"));
        Assert.assertEquals(expectedRequest.getBoolean("completed"),jsonPath.getBoolean("completed"));


    }
}
