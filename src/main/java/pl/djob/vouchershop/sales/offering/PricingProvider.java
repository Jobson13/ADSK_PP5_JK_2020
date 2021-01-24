package pl.djob.vouchershop.sales.offering;

public interface PricingProvider {
    ProductPricing getForProduct(String productId);
}