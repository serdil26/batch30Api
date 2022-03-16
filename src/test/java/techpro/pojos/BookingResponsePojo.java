package techpro.pojos;

public class BookingResponsePojo {
    //response body  {
    //                            "bookingid": 11,
    //                            "booking": {
    //                                "firstname": "Selim",
    //                                "lastname": "Ak",
    //                                "totalprice": 15000,
    //                                "depositpaid": true,
    //                                "bookingdates": {
    //                                    "checkin": "2020-09-09",
    //                                    "checkout": "2020-09-21"
    //                                }

    private int bookingId;
    private BookingPojo booking;

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public BookingPojo getBooking() {
        return booking;
    }

    public void setBooking(BookingPojo booking) {
        this.booking = booking;
    }

    public BookingResponsePojo() {
    }

    public BookingResponsePojo(int bookingId, BookingPojo booking) {
        this.bookingId = bookingId;
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "BookingResponsePojo{" +
                "bookingId=" + bookingId +
                ", booking=" + booking +
                '}';
    }
}
