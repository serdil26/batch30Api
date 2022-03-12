package techpro.day06;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import techpro.testBase.DummyTestBase;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest09 extends DummyTestBase {

    /*
    http://dummy.restapiexample.com/api/v1/employees
url ine bir istek gönderildiğinde,
status kodun 200,
gelen body de,
5. çalışanın isminin "Airi Satou" olduğunu ,
6. çalışanın maaşının "372000" olduğunu ,
Toplam 24 tane çalışan olduğunu,
"Rhona Davidson" ın employee lerden biri olduğunu
"21", "23", "61" yaşlarında employeeler olduğunu test edin
     */
@Test
    public void test(){
    spec03.pathParam("para1", "employees");

    Response response= given().spec(spec03).when().get("/{para1}");
    response.prettyPrint();

    response.then().assertThat().statusCode(200);

    JsonPath jsonPath=response.jsonPath();
    Assert.assertEquals("Airi Satou", jsonPath.getString("data.employee_name[4]"));
    Assert.assertEquals(372000, jsonPath.getInt("data.employee_salary[5]"));
    Assert.assertEquals(24, jsonPath.getList("data.id").size());
    Assert.assertTrue(jsonPath.getList("data.employee_name").contains("Rhona Davidson"));

    List<Integer> expectedAges= new ArrayList<>();
    expectedAges.add(21);
    expectedAges.add(23);
    expectedAges.add(61);
    Assert.assertTrue(jsonPath.getList("data.employee_age").containsAll(expectedAges));




}


}
