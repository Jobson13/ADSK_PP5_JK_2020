package pl.djob.vouchershop.sales;

public class PaymentDetails {

    private final String paymentUrl;
    private final String paymentId;
    private final String reservationId;

    public PaymentDetails(String paymentUrl, String paymentId, String reservationId) {

        this.paymentUrl = paymentUrl;
        this.paymentId = paymentId;
        this.reservationId = reservationId;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getReservationId() {
        return reservationId;
    }
}
