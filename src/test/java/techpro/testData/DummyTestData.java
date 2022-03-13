package techpro.testData;

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
}
