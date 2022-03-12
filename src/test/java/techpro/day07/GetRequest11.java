package techpro.day07;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import techpro.testBase.JsonPlaceHolderTestBase;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest11 extends JsonPlaceHolderTestBase {
       /*
https://jsonplaceholder.typicode.com/todos/2 url ‘ine istek gönderildiğinde,
 Dönen response un
 Status kodunun 200, dönen body de,
 "completed": değerinin false
"title”: değerinin “quis ut nam facilis et officia qui”
"userId" sinin 1 ve
header değerlerinden
 "Via" değerinin “1.1 vegur” ve
 "Server" değerinin “cloudflare” olduğunu test edin…
 */

    /*
    url oluştur
    --expected data
    request gönder
   -- actual data
    assertion
     */
    @Test
    public void test01(){
        spec01.pathParams("para1","todos", "para2", 2);
        HashMap<String, Object> expectedData=new HashMap<>();
        expectedData.put("status", 200);
        expectedData.put("via","1.1 vegur" );
        expectedData.put("Server","cloudflare");
        expectedData.put("userId", 1);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);

        System.out.println(expectedData);

        Response response= given().spec(spec01).when().get("/{para1}/{para2}");
        response.prettyPrint();

        //MATCHERS CLASS ILE ASSERTION
        response.then().statusCode((int) expectedData.get("status")).
                headers("via", equalTo(expectedData.get("via")),
                "Server", equalTo(expectedData.get("Server"))).
                body("userId", equalTo(expectedData.get("userId")),
                        "title", equalTo(expectedData.get("title")),
                        "completed", equalTo(expectedData.get("completed")));

        //JSON PATH ILE ASSERTION
        JsonPath jsonPath=response.jsonPath();
        Assert.assertEquals(expectedData.get("status"), response.statusCode());
        Assert.assertEquals(expectedData.get("via"), response.getHeader("via"));
        Assert.assertEquals(expectedData.get("Server"), response.getHeader("Server"));
        Assert.assertEquals(expectedData.get("userId"), jsonPath.getInt("userId"));
        Assert.assertEquals(expectedData.get("title"), jsonPath.getString("title"));
        Assert.assertEquals(expectedData.get("completed"), jsonPath.getBoolean("completed"));






    }
}
