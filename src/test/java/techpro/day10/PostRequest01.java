package techpro.day10;

import io.restassured.response.Response;
import org.junit.Test;
import techpro.testBase.DummyTestBase;
import techpro.testData.DummyTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequest01 extends DummyTestBase {
    /*
    http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
{
 "name":"Ahmet Aksoy",
 "salary":"1000",
 "age":"18",
  }
gönderildiğinde,Status kodun 200 olduğunu ve dönen response body nin ,
{
 "status": "success",
 "data": {
 “id”:…
 },
 "message": "Successfully! Record has been added."
 }
olduğunu test edin
     */
    @Test
    public void test01(){
        spec03.pathParam("para1", "create");

        DummyTestData obje= new DummyTestData();
        //post request yaparken body gondermek zorundayiz. test data class'inda olusturdugumuz request body'i burada cagiriyoruz
       HashMap<String, String> requestBodyMap= obje.setupRequestBody();

       HashMap<String, Object> expectedDataMap= obje.setupExpectedData();

       Response response = given().spec(spec03).body(requestBodyMap).when().post("/{para1}");
       response.prettyPrint();




    }
}
