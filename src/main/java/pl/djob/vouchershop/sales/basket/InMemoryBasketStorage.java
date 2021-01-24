package pl.djob.vouchershop.sales.basket;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryBasketStorage {
    private final Map<String, Basket> baskets;

    public InMemoryBasketStorage() {
        this.baskets = new ConcurrentHashMap<>();
    }

    public Optional<Basket> getBasket(String customerId) {
        return Optional.ofNullable(baskets.get(customerId));
    }

    public void addForCustomer(String customerId, Basket basket) {
        baskets.put(customerId, basket);
    }
}