package techpro.day14_sonders;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ReqresToken {

    public String tokenAl(){
        String url="https://reqres.in/api/login";

        HashMap<String, String> requestBody= new HashMap<>();
        requestBody.put("email", "eve.holt@reqres.in");
        requestBody.put("password", "pistol");
       // System.out.println("request body =" +requestBody);

        Response response= given().
                           contentType(ContentType.JSON).
                           body(requestBody). //auth().basic("admin", "password"). >>yazilabilir ya da yazilmayabilir
                           when().
                           post(url);
        //response.prettyPrint();

        JsonPath json= response.jsonPath();
        String token= json.getString("token");
        System.out.println("Token= " + token);


        return token;
    }


}
