package pl.djob.vouchershop.sales;

interface PaymentGateway {
    PaymentDetails registerFor(Reservation reservation, ClientData clientData);
}
