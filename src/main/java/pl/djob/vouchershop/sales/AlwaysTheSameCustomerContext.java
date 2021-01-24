package pl.djob.vouchershop.sales;

import java.util.UUID;

public class AlwaysTheSameCustomerContext implements CurrentCustomerContext {
    @Override
    public String getCustomerId() {
        return "customer_1";
    }
}
