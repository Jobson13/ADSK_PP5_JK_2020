package pl.djob.vouchershop.catalog;

import java.util.List;

public interface ProductStorage {
    void save(Product newProduct);

    boolean isExists(String productId);

    Product load(String productId);

    List<Product> allProducts();
}
