package pl.djob.vouchershop.catalog;

import org.junit.Assert;
import org.junit.Test;

public class ProductCatalogTest {
    @Test
    public void itAllowsToRegisterNewProduct(){
        //Arrange
        ProductCatalog catalog = thereIsProductCatalog();
        //Act
        String productId = catalog.registerProduct();
        //Assert
        Assert.assertTrue(catalog.isExists(productId));
    }

    private static ProductCatalog thereIsProductCatalog() {
        return new ProductCatalog();
    }

}
