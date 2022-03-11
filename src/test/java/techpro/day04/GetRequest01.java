package techpro.day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest01 {
/* https://restful-booker.herokuapp.com/booking/3 adresine bir request gonderildiginde
donecek response icin
Status code=200
Content type= json
ve status line'in HTTP/1.1 200ok
oldugunu test edin
 */
    @Test
        public void test01(){
        //1-URI olustur
        String url= "https://restful-booker.herokuapp.com/booking/3";
        //2-expected result olustur

        //3-REQUEST gonder
       Response response= given().when().get(url);
       //response.prettyPrint();

        //4-actual result oluştur

        //response body ile ilgili işlem yapmayacağımız için şimdi oluşturmayacağız

        //5-doğrulama yap(assertion)
        System.out.println("status code= " + response.statusCode());
        System.out.println("content type= "+ response.contentType());
        System.out.println("status line = "+ response.statusLine());
        System.out.println("headers= " +response.getHeaders());

       // Assert.assertEquals(200, response.statusCode());
       // Assert.assertEquals("application/json; charset=utf-8", response.contentType());
       // Assert.assertEquals("HTTP/1.1 200 OK", response.getStatusLine());

        response.then().assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");


    }





}

