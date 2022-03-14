package techpro.day09;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import techpro.testBase.DummyTestBase;
import techpro.testData.DummyTestData;

import java.util.*;

import static io.restassured.RestAssured.given;

public class GetRequest14 extends DummyTestBase {
     /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
En yüksek maaşın 725000 olduğunu,
En küçük yaşın 19 olduğunu,
İkinci en yüksek maaşın 675000
olduğunu test edin.
     */
    @Test
    public void test01(){
        spec03.pathParam("para1", "employees");

        DummyTestData expectedObje= new DummyTestData();
        HashMap<String, Integer> expectedData= expectedObje.setupTestData02();
        System.out.println("expected data bilgileri = "+expectedData);

        Response response= given().spec(spec03).when().get("/{para1}");
        //response.prettyPrint();

        //DESERIALIZATION ILE ASSERT
        HashMap<String, Object> actualData= response.as(HashMap.class);
        System.out.println("actual data bilgileri= "+ actualData);
        //Status kodun 200 olduğunu
        Assert.assertEquals(expectedData.get("status"),(Integer) response.getStatusCode());
        //En yüksek maaşın 725000 olduğunu
        List<Integer> maasListesi=new ArrayList<>();
        int dataSize=((List)actualData.get("data")).size();
        for(int i=0; i< dataSize; i++){
           maasListesi.add ((Integer) ((Map)((List)actualData.get("data")).get(i)).get("employee_salary") );
        }
        Collections.sort(maasListesi);

        Assert.assertEquals(expectedData.get("enYuksekMaas"), maasListesi.get(maasListesi.size()-1));

        //İkinci en yüksek maaşın 675000
        Assert.assertEquals(expectedData.get("ikinciYuksekMaas"),maasListesi.get(maasListesi.size()-2));

        //En küçük yaşın 19 olduğunu,
        List<Integer> yasListesi=new ArrayList<Integer>();
        int dataSize2=((List)actualData.get("data")).size();
        for (int i=0;i<dataSize2; i++){
            yasListesi.add(  (Integer) ((Map)((List<?>) actualData.get("data")).get(i)).get("employee_age")   );

        }

        Collections.sort(yasListesi);
        Assert.assertEquals(expectedData.get("enKucukYas"),yasListesi.get(0));

        //JSONPATH ILE ASSERTION

        JsonPath json =response.jsonPath();
           //En yüksek maaşın 725000 olduğunu,
        List<Integer> maasListesijson=json.getList("data.employee_salary");
        Collections.sort(maasListesijson);
        Assert.assertEquals(expectedData.get("enYuksekMaas"),maasListesijson.get(maasListesijson.size()-1));
        //İkinci en yüksek maaşın 675000
        Assert.assertEquals(expectedData.get("ikinciYuksekMaas"),maasListesijson.get(maasListesijson.size()-2));
        //En küçük yaşın 19 olduğunu

        List<Integer> yasListesijson=json.getList("data.employee_age");
        Collections.sort(yasListesijson);
        Assert.assertEquals(expectedData.get("enKucukYas"),yasListesijson.get(0));



      



    }

}
