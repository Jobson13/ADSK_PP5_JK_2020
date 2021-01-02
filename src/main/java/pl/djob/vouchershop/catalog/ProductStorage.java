package pl.djob.vouchershop.catalog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ProductStorage {
    private final HashMap<String, Product> products;

    public ProductStorage() {
        this.products =  new HashMap<>();
    }

    public void save(Product newProduct) {
        products.put(newProduct.getId(), newProduct);

    }

    public boolean isExists(String productId) {
        return products.containsKey(productId);
    }

    public Product load(String productId) {
        return products.get(productId);
    }

    public List<Product> allProducts() {
        return new ArrayList<>(products.values());

    }
}
