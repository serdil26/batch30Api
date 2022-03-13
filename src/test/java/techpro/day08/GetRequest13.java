package techpro.day08;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import techpro.testBase.DummyTestBase;
import techpro.testData.DummyTestData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest13 extends DummyTestBase {
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

        //DESERIALIZATION ILE ASSERTION
        HashMap<String, Object> actualData= response.as(HashMap.class);
        //Status kodun 200 olduğunu,
        Assert.assertEquals(expectedData.get("status"), response.statusCode());
        //5. Çalışan isminin "Airi Satou" olduğunu
        Assert.assertEquals(expectedData.get("besinciCalisan"),
                ((Map)((List)actualData.get("data")).get(4)).get("employee_name"));
        //çalışan sayısının 24 olduğunu
        Assert.assertEquals(expectedData.get("calisanSayisi"), ((List<?>) actualData.get("data")).size());
        //Sondan 2. çalışanın maaşının 106450 olduğunu
        //once actuladata'dan bize donen list'in size'ini almaliyiz
        int dataSize= ((List<?>) actualData.get("data")).size();
        Assert.assertEquals(expectedData.get("sondanIkincininMaasi"),
                ((Map)((List<?>) actualData.get("data")).get(dataSize-2)).get("employee_salary"));
        //40,21 ve 19 yaslarında çalışanlar olup olmadığını
        List<Integer> actualYasListesi= new ArrayList<>();

        for (int i=0; i<dataSize; i++){
            actualYasListesi.add((Integer) ((Map)((List) actualData.get("data")).get(i)).get("employee_age") );
        }
        Assert.assertTrue(actualYasListesi.containsAll(  (List)expectedData.get("calisanYaslari"))  );

        //11. Çalışan bilgilerinin dogrulunu
        Assert.assertEquals(((Map)expectedData.get("onbirinciCalisan")).get("employee_name"),
                ((Map) ((List<?>) actualData.get("data")).get(10)).get("employee_name"));


        Assert.assertEquals(((Map<?, ?>) expectedData.get("onbirinciCalisan")).get("employee_salary"),

                ((Map) ((List<?>) actualData.get("data")).get(10)).get("employee_salary"));

        Assert.assertEquals(((Map<?, ?>) expectedData.get("onbirinciCalisan")).get("employee_age"),
                ((Map) ((List<?>) actualData.get("data")).get(10)).get("employee_age"));

        //Assert.assertEquals(((Map<?, ?>) expectedData.get("onbirinciCalisan")).get("profile_image"),
          //      ((Map) ((List<?>) actualData.get("data")).get(10)).get("profile_image"));




    }
}
