package pl.djob.vouchershop.catalog;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.UUID;

public class ProductCatalog {
    private final HashMap<String, Product> products;

    public ProductCatalog() {
        this.products = new HashMap<String, Product>();
    }

    public String registerProduct() {
        Product newProduct = new Product(UUID.randomUUID());
        products.put(newProduct.getId(), newProduct);
        return newProduct.getId();
    }

    public boolean isExists(String productId) {
        return products.containsKey(productId);
    }

    public Product load(String productId) {
        return products.get(productId);
    }

    public void updateDetails(String productId, String productDesc, String productPicture) {
        Product loaded = products.get(productId);
        loaded.setDescription(productDesc);
        loaded.setPicture(productPicture);

    }

    public void applyPrice(String productId, BigDecimal price) {
        Product loaded = products.get(productId);
        loaded.setPrice(price);


    }
}
