package techpro.pojos;

public class BookingPojo {
    //"booking": {
    //                                "firstname": "Selim",
    //                                "lastname": "Ak",
    //                                "totalprice": 15000,
    //                                "depositpaid": true,
    //"bookingdates": {
      //  "checkin": "2020-09-09",
        //        "checkout": "2020-09-21"

    private String firstName;
    private String lastName;
    private int totalPrice;
    private boolean depositpaid;
    private BookingDatesPojo bookingDates;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingDatesPojo getBookingDates() {
        return bookingDates;
    }

    public void setBookingDates(BookingDatesPojo bookingDates) {
        this.bookingDates = bookingDates;
    }

    public BookingPojo() {
    }

    public BookingPojo(String firstName, String lastName, int totalPrice, boolean depositpaid, BookingDatesPojo bookingDates) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPrice = totalPrice;
        this.depositpaid = depositpaid;
        this.bookingDates = bookingDates;
    }

    @Override
    public String toString() {
        return "BookingPojo{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", totalPrice=" + totalPrice +
                ", depositpaid=" + depositpaid +
                ", bookingDates=" + bookingDates +
                '}';
    }
}
