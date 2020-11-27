package pl.djob.vouchershop.catalog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class ProductCatalogConfiguration {


    ProductCatalog myProductCatalog(){
        return new ProductCatalog();
    }

    @Bean
    ProductCatalog myFixtureAwareCatalog(){
        ProductCatalog productCatalog = new ProductCatalog();

        var p1 =productCatalog.registerProduct();
        productCatalog.applyPrice(p1, BigDecimal.valueOf(15.15));
        productCatalog.updateDetails(p1, "Nice product1", "Nice picture1");
        
        var p2 =productCatalog.registerProduct();
        productCatalog.applyPrice(p2, BigDecimal.valueOf(115.115));
        productCatalog.updateDetails(p2, "Nice product2", "Nice picture2");

        var p3 =productCatalog.registerProduct();
        productCatalog.applyPrice(p3, BigDecimal.valueOf(115.115));
        productCatalog.updateDetails(p3, "Nice product3", "Nice picture3");


        return productCatalog;
    }
}
