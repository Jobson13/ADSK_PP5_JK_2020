package pl.djob.vouchershop.sales;

import org.junit.Before;
import org.junit.Test;
import pl.djob.vouchershop.sales.basket.InMemoryBasketStorage;
import pl.djob.vouchershop.sales.offering.Offer;
import pl.djob.vouchershop.sales.offering.OfferMaker;
import pl.djob.vouchershop.sales.offering.ProductCatalogPricingProvider;

import java.math.BigDecimal;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderingTest extends SalesTestCase {
    @Before
    public void setUp() {
        this.basketStorage = new InMemoryBasketStorage();
        this.customerId = UUID.randomUUID().toString();
        this.userContext = () -> customerId;
        this.productCatalog = thereIsProductCatalog();
        this.offerMaker = thereIsOfferMaker();
        this.paymentGateway = thereIsPaymenyGateway();
    }

    private PaymentGateway thereIsPaymenyGateway() {
        return ((reservation, clientData) -> new PaymentDetails("url", "pId", reservation.getId()));
    }

    @Test
    public void itCreateOfferBasedSelectedProducts() {
        SalesFacade salesModule = thereIsSalesModule();
        String productId = thereIsProductAvailable();
        String customerId = thereIsCustomerWhoIsDoingSomeShoping();

        salesModule.addToBasket(productId);
        salesModule.addToBasket(productId);

        Offer offer = salesModule.getCurrentOffer();

        assertThat(offer.getTotal()).isEqualTo(BigDecimal.valueOf(40.40));
    }

    @Test
    public void itCreateReservationBasedOnCurrentOffer() {
        SalesFacade salesModule = thereIsSalesModule();
        String productId = thereIsProductAvailable();
        String customerId = thereIsCustomerWhoIsDoingSomeShoping();

        salesModule.addToBasket(productId);
        salesModule.addToBasket(productId);

        Offer seenOffer = salesModule.getCurrentOffer();

        PaymentDetails paymentDetails = salesModule.acceptOffer(thereIsExampleClientData());

        assertThat(paymentDetails.getPaymentUrl()).isNotNull();
        assertThat(paymentDetails.getPaymentId()).isNotNull();
        assertThat(paymentDetails.getReservationId()).isNotNull();
    }

    private ClientData thereIsExampleClientData() {
        return ClientData.builder()
                .email("john.doe@example.dev")
                .firstname("John")
                .lastname("doe")
                .build();
    }

    private OfferMaker thereIsOfferMaker() {
        return new OfferMaker(new ProductCatalogPricingProvider(productCatalog));
    }
}
