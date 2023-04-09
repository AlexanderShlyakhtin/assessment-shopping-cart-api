package dev.alex.projects.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartRs {
    private BigDecimal subtotalBeforeDiscounts = BigDecimal.valueOf(0).setScale(2);
    private BigDecimal discountTotal = BigDecimal.valueOf(0).setScale(2);
    private BigDecimal subtotalAfterDiscounts = BigDecimal.valueOf(0).setScale(2);
    private BigDecimal taxableSubtotalAfterDiscounts = BigDecimal.valueOf(0).setScale(2);
    private BigDecimal taxTotal = BigDecimal.valueOf(0).setScale(2);
    private BigDecimal grandTotal = BigDecimal.valueOf(0).setScale(2);

    public BigDecimal addSubtotalBeforeDiscounts(BigDecimal number) {
        return subtotalBeforeDiscounts = subtotalBeforeDiscounts.add(number);
    }
    public BigDecimal addDiscountTotal(BigDecimal number) {
        return discountTotal = discountTotal.add(number);
    }
    public BigDecimal addSubtotalAfterDiscounts(BigDecimal number) {
        return subtotalAfterDiscounts = subtotalAfterDiscounts.add(number);
    }
    public BigDecimal addTaxableSubtotalAfterDiscounts(BigDecimal number) {
        return taxableSubtotalAfterDiscounts = taxableSubtotalAfterDiscounts.add(number);
    }
    public BigDecimal addTaxTotal(BigDecimal number) {
        return taxTotal = taxTotal.add(number);
    }
    public BigDecimal addGrandTotal(BigDecimal number) {
        return grandTotal = grandTotal.add(number);
    }

}
