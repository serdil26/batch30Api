package techpro.testData;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DummyTestData {
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
    public HashMap<String, Object> setupTestData(){
        List<Integer> ages= Arrays.asList(40,21,19); //yaslar icin bir liste olusturuldu

        HashMap<String, Object> onbirinciKisi= new HashMap<>(); //11.kisi icin bir hashmap olusturuldu
        onbirinciKisi.put("id", 11);
        onbirinciKisi.put("employee_name", "Jena Gaines");
        onbirinciKisi.put("employee_salary", 90560);
        onbirinciKisi.put("employee_age", 30);
        onbirinciKisi.put("employee_image","") ;
        HashMap<String, Object> expectedData= new HashMap<>();
        expectedData.put("status", 200);
        expectedData.put("besinciCalisan", "Airi Satou");
        expectedData.put("calisanSayisi", 24);
        expectedData.put("sondanIkincininMaasi", 106450);
        expectedData.put("calisanYaslari", ages);
        expectedData.put("onbirinciCalisan", onbirinciKisi);


        return expectedData;

    }
     /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
En yüksek maaşın 725000 olduğunu,
En küçük yaşın 19 olduğunu,
İkinci en yüksek maaşın 675000
olduğunu test edin.
     */
    public HashMap<String, Integer> setupTestData02(){
        HashMap<String, Integer> expectedData=new HashMap<>();
        expectedData.put("status", 200);
        expectedData.put("enYuksekMaas", 725000);
        expectedData.put("enKucukYas", 19);
        expectedData.put("ikinciYuksekMaas", 675000);

        return expectedData;
    }
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
    public HashMap<String, String> setupRequestBody(){
        HashMap<String, String> requestBody= new HashMap<>();
        requestBody.put("name", "sezgin");
        requestBody.put("salary", "3000");
        requestBody.put("age", "44");

        return requestBody;


    }
    public HashMap<String, Object> setupExpectedData(){
        HashMap<String, Object> data=new HashMap<>();
        data.put("name", "sezgin");
        data.put("salary", "3000");
        data.put("age", "44");

        HashMap<String, Object> expectedData=new HashMap<>();
        expectedData.put("statusCode", 200  );
        expectedData.put("status", "success"  );
        //expectedData.put("data", data );
        expectedData.put("message", "Successfully! Record has been added." );

        return expectedData;

    }

    /*
    http://dummy.restapiexample.com/api/v1/delete/2 bir DELETE request gönderdiğimde
 Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
 {
 "status": "success",
 "data": "2",
 "message": "Successfully! Record has been deleted"
 }
     */

    public JSONObject setupDeleteData(){
        JSONObject expectedData=new JSONObject();
        expectedData.put("status", "success");
        expectedData.put("data","2");
        expectedData.put("message", "Successfully! Record has been deleted");

        return expectedData;
    }


}
