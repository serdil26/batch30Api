package techpro.day12;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import techpro.pojos.BookingDatesPojo;
import techpro.pojos.BookingPojo;
import techpro.pojos.BookingResponsePojo;
import techpro.testBase.HerOkuAppTestBase;

import static io.restassured.RestAssured.given;

public class PostRequestWithPojo02 extends HerOkuAppTestBase {
     /*
    https://restful-booker.herokuapp.com/booking
    request body {
                   "firstname": "Selim",
                   "lastname": "Ak",
                   "totalprice": 15000,
                   "depositpaid": true,
                   "bookingdates": {
                       "checkin": "2020-09-09",
                       "checkout": "2020-09-21"
                    }
                 }
   Status code is 200
    response body  {
                            "bookingid": 11,
                            "booking": {
                                "firstname": "Selim",
                                "lastname": "Ak",
                                "totalprice": 15000,
                                "depositpaid": true,
                                "bookingdates": {
                                    "checkin": "2020-09-09",
                                    "checkout": "2020-09-21"
                                }
                            }
                         }
     */
    @Test
    public void test(){
        spec02.pathParam("parametre1","booking");

        BookingDatesPojo bookingdates=new BookingDatesPojo("2020-09-09","2020-09-21");
        System.out.println(bookingdates);

        BookingPojo expectedData=
                new BookingPojo("Selim","Ak",15000,true,bookingdates);
        System.out.println(expectedData);

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec02).
                auth().
                basic("admin","password123").
                body(expectedData).
                when().
                post("/{parametre1}");

        response.prettyPrint();

   BookingResponsePojo actualData= response.as(BookingResponsePojo.class);

        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(expectedData.getFirstName(),actualData.getBooking().getFirstName());
        Assert.assertEquals(expectedData.getLastName(),actualData.getBooking().getLastName());
        Assert.assertEquals(expectedData.getTotalPrice(),actualData.getBooking().getTotalPrice());
        Assert.assertEquals(expectedData.isDepositpaid(),actualData.getBooking().isDepositpaid());
        Assert.assertEquals(expectedData.getBookingDates().getCheckin(),
                actualData.getBooking().getBookingDates().getCheckin());

        Assert.assertEquals(expectedData.getBookingDates().getCheckout(),
                actualData.getBooking().getBookingDates().getCheckout());





    }

}
