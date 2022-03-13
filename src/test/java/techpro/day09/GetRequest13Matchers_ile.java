package techpro.day09;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import techpro.testBase.DummyTestBase;
import techpro.testData.DummyTestData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest13Matchers_ile extends DummyTestBase {
     /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
      5. Çalışan isminin "Airi Satou" olduğunu ,
      çalışan sayısının 24 olduğunu,
    Sondan 2. çalışanın maaşının 106450 olduğunu
40,21 ve 19 yaslarında çalışanlar olup olmadığını
11. Çalışan bilgilerinin
    {
 “id”:”11”
        "employee_name": "Jena Gaines",
            "employee_salary": "90560",
            "employee_age": "30",
            "profile_image": "" }
} gibi olduğunu test edin.
*/

    @Test
    public void test(){
        //url olustur
        spec03.pathParam("para", "employees");
        //expected data olustur
        DummyTestData expectedObjesi= new DummyTestData();
        HashMap<String, Object> expectedData= expectedObjesi.setupTestData();
        //request gonder
        Response response=given().spec(spec03).when().get("/{para}");
        //response.prettyPrint();

        response.then().assertThat().statusCode((Integer)expectedData.get("statusCode")).
                body("data[4].employee_name",equalTo(expectedData.get("besinciCalisan")),
                        "data.id" ,hasSize((Integer) expectedData.get("calisanSayisi")) ,
                        "data[-2].employee_salary",equalTo(expectedData.get("sondanIkincininMaasi")),
                        "data.employee_age",hasItems(((List)expectedData.get("calisanYaslari")).get(0),
                                ((List<?>) expectedData.get("calisanYaslari")).get(1),
                                ((List<?>) expectedData.get("calisanYaslari")).get(2)),
                        "data[10].employee_name",equalTo(((Map)expectedData.get("onbirinciCalisan")).get("employee_name")),
                        "data[10].employee_salary",equalTo(((Map<?, ?>) expectedData.get("onbirinciCalisan")).get("employee_salary")),
                        "data[10].employee_age",equalTo(((Map<?, ?>) expectedData.get("onbirinciCalisan")).get("employee_age")),
                        "data[10].profile_image",equalTo(((Map<?, ?>) expectedData.get("onbirinciCalisan")).get("profile_image")));

    }

}
