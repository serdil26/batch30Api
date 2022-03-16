package techpro.pojos;

public class BookingDatesPojo {
    //"bookingdates": {
    //                                    "checkin": "2020-09-09",
    //                                    "checkout": "2020-09-21"
    //                                }

    //private olarak tanimliyoruz
    private String checkin;
    private String checkout;

    //getter-setter
    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }
//parametreli constructor
    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }
//parametresiz constructor
    public BookingDatesPojo() {
    }
//toString methodu olustur
    @Override
    public String toString() {
        return "BookingDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
