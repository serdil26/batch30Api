package techpro.day07;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import techpro.testBase.JsonPlaceHolderTestBase;
import techpro.testData.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest11TestData extends JsonPlaceHolderTestBase {
    @Test
    public void test01(){
        spec01.pathParams("para1","todos", "para2", 2);

        JsonPlaceHolderTestData expectedObjesi=new JsonPlaceHolderTestData();
        HashMap<String, Object> expectedData= (HashMap<String, Object>) expectedObjesi.setupTestData();

        System.out.println("expected data= " +expectedData);

        Response response= given().spec(spec01).when().get("/{para1}/{para2}");
        response.prettyPrint();

        //1. YONTEM>> MATCHERS CLASS ILE ASSERTION
        response.then().statusCode((int) expectedData.get("status")).
                headers("via", equalTo(expectedData.get("via")),
                        "Server", equalTo(expectedData.get("Server"))).
                body("userId", equalTo(expectedData.get("userId")),
                        "title", equalTo(expectedData.get("title")),
                        "completed", equalTo(expectedData.get("completed")));

        //2. YONTEM>> JSON PATH ILE ASSERTION
        JsonPath jsonPath=response.jsonPath();
        Assert.assertEquals(expectedData.get("status"), response.statusCode());
        Assert.assertEquals(expectedData.get("via"), response.getHeader("via"));
        Assert.assertEquals(expectedData.get("Server"), response.getHeader("Server"));
        Assert.assertEquals(expectedData.get("userId"), jsonPath.getInt("userId"));
        Assert.assertEquals(expectedData.get("title"), jsonPath.getString("title"));
        Assert.assertEquals(expectedData.get("completed"), jsonPath.getBoolean("completed"));

        //3. YONTEM>> DESERIALIZATION ILE ASSERTION

        HashMap<String, Object> actualData=response.as(HashMap.class);
        Assert.assertEquals(expectedData.get("userId"), actualData.get("userId"));
        Assert.assertEquals(expectedData.get("title"), actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"), actualData.get("completed"));

        System.out.println("actual data= "+ actualData);






    }
}
