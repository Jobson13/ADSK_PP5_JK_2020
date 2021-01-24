package pl.djob.vouchershop.sales;

import org.junit.Before;
import org.junit.Test;
import pl.djob.vouchershop.sales.basket.Basket;
import pl.djob.vouchershop.sales.basket.InMemoryBasketStorage;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CollectingProductsTest extends SalesTestCase {

    @Before
    public void setUp() {
        this.basketStorage = new InMemoryBasketStorage();
        this.customerId = UUID.randomUUID().toString();
        this.userContext = () -> customerId;
        this.productCatalog = thereIsProductCatalog();
    }

    @Test
    public void itAllowAddProductToBasket() {
        SalesFacade salesModule = thereIsSalesModule();
        String productId = thereIsProductAvailable();
        String customerId = thereIsCustomerWhoIsDoingSomeShoping();

        salesModule.addToBasket(productId);

        thereIsXProductsInCustomerBasket(1, customerId);
    }

    private void thereIsXProductsInCustomerBasket(int productsCount, String customerId) {
        Basket basket = basketStorage.getBasket(customerId)
                .orElse(Basket.empty());

        assertThat(basket.getProductsCount()).isEqualTo(productsCount);
    }
}
