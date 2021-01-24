package pl.djob.vouchershop.sales.offering;

import pl.djob.vouchershop.catalog.Product;
import pl.djob.vouchershop.catalog.ProductCatalog;
import pl.djob.vouchershop.sales.offering.PricingProvider;
import pl.djob.vouchershop.sales.offering.ProductPricing;

public class ProductCatalogPricingProvider implements PricingProvider {
    private final ProductCatalog productCatalog;

    public ProductCatalogPricingProvider(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    @Override
    public ProductPricing getForProduct(String productId) {
        Product product = productCatalog.load(productId);
        return new ProductPricing(product.getPrice(), product.getDescription());
    }
}
