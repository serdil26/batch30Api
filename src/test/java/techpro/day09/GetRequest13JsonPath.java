package techpro.day09;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import techpro.testBase.DummyTestBase;
import techpro.testData.DummyTestData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest13JsonPath extends DummyTestBase {
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
    public void test() {
        //url olustur
        spec03.pathParam("para", "employees");
        //expected data olustur
        DummyTestData expectedObjesi = new DummyTestData();
        HashMap<String, Object> expectedData = expectedObjesi.setupTestData();
        //request gonder
        Response response = given().spec(spec03).when().get("/{para}");
        //response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(expectedData.get("status"), response.getStatusCode());
        Assert.assertEquals(expectedData.get("besinciCalisan"), jsonPath.getString("data.employee_name[4]"));
        Assert.assertEquals(expectedData.get("calisanSayisi"), jsonPath.getList("data.id").size());
        Assert.assertEquals(expectedData.get("sondanIkincininMaasi"), jsonPath.getInt("data.employee_salary[-2]"));
        Assert.assertTrue(jsonPath.getList("data.employee_age").containsAll((List) expectedData.get("calisanYaslari")));
        Assert.assertEquals(((Map) expectedData.get("onbirinciCalisan")).get("id"),
                jsonPath.getInt("data[10].id"));

        Assert.assertEquals(((Map<?, ?>) expectedData.get("onbirinciCalisan")).get("employee_name"),
                jsonPath.getString("data[10].employee_name"));

        Assert.assertEquals(((Map<?, ?>) expectedData.get("onbirinciCalisan")).get("employee_salary"),
                jsonPath.getInt("data[10].employee_salary"));

        Assert.assertEquals(((Map<?, ?>) expectedData.get("onbirinciCalisan")).get("employee_age"),
                jsonPath.getInt("data[10].employee_age"));





    }
}
