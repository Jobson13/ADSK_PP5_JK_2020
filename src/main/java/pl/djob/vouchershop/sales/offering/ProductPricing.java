package pl.djob.vouchershop.sales.offering;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class ProductPricing {
    private final BigDecimal unitPrice;
    private final String description;
}
