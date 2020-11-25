package pl.djob.vouchershop.catalog;

import java.util.UUID;

public class ProductCatalog {
    public String registerProduct() {
        return UUID.randomUUID().toString();
    }

    public boolean isExists(String productId) {
        return true;
    }

    public Product load(String productId) {
        return null;
    }

    public void updateDetails(String productId, String productDesc, String productPicture) {

    }
}
