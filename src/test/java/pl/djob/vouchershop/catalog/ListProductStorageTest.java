package pl.djob.vouchershop.catalog;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;

public class ListProductStorageTest {
    @Test
    public void itAllowAddProduct(){
        //Arrange
        ProductStorage productStorage = new ListProductStorage();
        Product product = thereIsProduct();
        //A
        productStorage.save(product);
        //A
        Assert.assertTrue(productStorage.isExists(product.getId()));
    }

    private Product thereIsProduct() {
        return new Product(UUID.randomUUID());
    }

    @Test
    public void itAllowLoadProducts(){

    }

    @Test
    public void itAllowCheckIfProductExist(){

    }

    @Test
    public void testIt() {
        assertThat("Ala ma kota").containsIgnoringCase("ala");
        assertThat(Arrays.asList("Damian", "Natalia"/*, "Emilka"*/))
                .hasSize(3)
                .contains("Damian", "Natalia")
                .doesNotContain("Michal");
    }
}
