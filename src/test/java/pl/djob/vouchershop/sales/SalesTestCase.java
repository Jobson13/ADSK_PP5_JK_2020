package pl.djob.vouchershop.sales;

import pl.djob.vouchershop.catalog.ProductCatalog;
import pl.djob.vouchershop.catalog.ProductCatalogConfiguration;
import pl.djob.vouchershop.sales.basket.InMemoryBasketStorage;
import pl.djob.vouchershop.sales.offering.OfferMaker;

import java.math.BigDecimal;
import java.util.UUID;

public class SalesTestCase {
    protected InMemoryBasketStorage basketStorage;
    protected CurrentCustomerContext userContext;
    protected ProductCatalog productCatalog;
    protected OfferMaker offerMaker;
    protected String customerId;
    protected PaymentGateway paymentGateway;

    protected static ProductCatalog thereIsProductCatalog() {
        return new ProductCatalogConfiguration().myProductCatalog();
    }

    protected String thereIsCustomerWhoIsDoingSomeShoping() {
        var id = UUID.randomUUID().toString();
        this.customerId = id;
        return id;
    }

    protected String thereIsProductAvailable() {
        String productId = productCatalog.registerProduct();
        productCatalog.applyPrice(productId, BigDecimal.valueOf(20.20));
        productCatalog.updateDetails(productId, "info", "pic");

        return productId;
    }

    protected SalesFacade thereIsSalesModule() {
        return new SalesFacade(userContext, basketStorage, productCatalog, offerMaker, paymentGateway);
    }
}
