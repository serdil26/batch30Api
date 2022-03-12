package techpro.day07;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import techpro.testBase.DummyTestBase;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest10 extends DummyTestBase {
 /*
    http://dummy.restapiexample.com/api/v1/employees
url ine bir istek gönderildiğinde
Dönen response un
 Status kodunun 200,
 1)10’dan büyük tüm id’leri ekrana yazdırın ve
10’dan büyük 14 id olduğunu,
 2)30’dan küçük tüm yaşları ekrana yazdırın ve
  bu yaşların içerisinde en büyük yaşın 23 olduğunu
 3)Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
  bunların içerisinde “Charde Marshall” olduğunu test edin
     */

    @Test
    public void test01(){
        spec03.pathParam("para1", "employees");

        Response response= given().spec(spec03).when().get("/{para1}");
        response.prettyPrint();
        //Status kodunun 200,
        Assert.assertEquals(200 ,response.statusCode());

        JsonPath jsonPath=response.jsonPath();
        //10’dan büyük tüm id’leri ekrana yazdırın
        List<Integer> idList= jsonPath.getList("data.findAll{it.id>10}.id");
        System.out.println("10dan buyuk id'ler= "+ idList);
        //10’dan büyük 14 id olduğunu
        Assert.assertEquals(14, idList.size());

        //30’dan küçük tüm yaşları ekrana yazdırın
        List<Integer> ages= jsonPath.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println("30 yas alti= " + ages);
        //bu yaşların içerisinde en büyük yaşın 23 olduğunu
        Collections.sort(ages);
        Assert.assertEquals((Integer)23,ages.get(ages.size()-1));

        //Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın
        List<String> isimListesi= jsonPath.getList("data.findAll{it.employee_salary>350000}.employee_name");
        System.out.println("Maasi 350000den fazla olanlarin isimleri = "+isimListesi);
        //bunların içerisinde “Charde Marshall” olduğunu test edin
        Assert.assertTrue(isimListesi.contains("Charde Marshall"));




    }

}
