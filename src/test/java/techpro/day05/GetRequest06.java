package techpro.day05;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import techpro.testBase.JsonPlaceHolderTestBase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest06 extends JsonPlaceHolderTestBase {
     /*
    https://jsonplaceholder.typicode.com/todos/123 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response’un
    status kodunun 200
    ve content type'inin "application/json"
    ve Headers'daki "Server" in "cloudflare"
    ve response body'deki "userId"'nin 7
    ve "title" in "esse et quis iste est earum aut impedit"
    ve "completed" bolumunun false oldugunu test edin
    */

    @Test
    public void test01(){
        String url= "https://jsonplaceholder.typicode.com/todos/123";

        Response response= given().when().get(url);
        response.prettyPrint();

        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                header("Server", "cloudflare").
                body("userId", equalTo(7),
                        "title", equalTo("esse et quis iste est earum aut impedit"),
                        "completed", equalTo(false));


    }

    @Test
    public void test02(){
        spec01.pathParams("para1", "todos", "para2", 123);
        //spec01 de base url var (parantez icindekiler de parametreleri ifade eder)
        //spec01 = https://jsonplaceholder.typicode.com (para1= todos, para2=123)>>>https://jsonplaceholder.typicode.com/todos/123

        Response response= given().spec(spec01).get("/{para1}/{para2}");
        response.prettyPrint();

        response.then().statusCode(200).
                contentType(ContentType.JSON).
                header("server", "cloudflare").
                body("title", equalTo("esse et quis iste est earum aut impedit"),
                        "userId", equalTo(7), "completed", equalTo(false));


    }
}
