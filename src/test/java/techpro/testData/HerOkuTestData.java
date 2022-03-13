package techpro.testData;

import java.util.HashMap;
import java.util.Map;

public class HerOkuTestData {
    public HashMap<String, Object> setupTestData(){

        HashMap<String, Object> bookingDates= new HashMap<>();
        bookingDates.put("checkin","2019-11-30");
        bookingDates.put("checkout","2021-11-15");

        HashMap<String, Object> expectedData= new HashMap<>();
        expectedData.put("firstname", "Susan");
        expectedData.put("lastname", "Wilson");
        expectedData.put("totalprice", 342);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingDates);

    return expectedData;
    }

}
