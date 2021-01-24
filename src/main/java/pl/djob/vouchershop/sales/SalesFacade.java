package pl.djob.vouchershop.sales;

import pl.djob.vouchershop.catalog.Product;
import pl.djob.vouchershop.catalog.ProductCatalog;
import pl.djob.vouchershop.sales.basket.Basket;
import pl.djob.vouchershop.sales.basket.InMemoryBasketStorage;

public class SalesFacade {

    private final CurrentCustomerContext currentSystemUserContext;
    private final InMemoryBasketStorage basketStorage;
    private final ProductCatalog productCatalog;

    public SalesFacade(CurrentCustomerContext currentSystemUserContext, InMemoryBasketStorage basketStorage, ProductCatalog productCatalog) {
        this.currentSystemUserContext = currentSystemUserContext;
        this.basketStorage = basketStorage;
        this.productCatalog = productCatalog;
    }

    public void addToBasket(String productId) {
        Basket basket = basketStorage.getBasket(getCurrentCustomerId())
                .orElse(Basket.empty());

        Product product = productCatalog.load(productId);

        basket.add(product);

        basketStorage.addForCustomer(getCurrentCustomerId(), basket);
    }

    private String getCurrentCustomerId() {
        return currentSystemUserContext.getCustomerId();
    }

    public void getCurrentOffer() {

    }

    public PaymentDetails acceptOffer() {
        return null;
    }
}