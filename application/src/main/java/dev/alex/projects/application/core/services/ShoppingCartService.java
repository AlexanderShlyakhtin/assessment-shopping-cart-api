package dev.alex.projects.application.core.services;

import dev.alex.projects.application.core.utill.ReadJsonObjectService;
import dev.alex.projects.application.dto.ShoppingCart;
import dev.alex.projects.application.dto.ShoppingCartRs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;

import static java.lang.Math.min;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final ReadJsonObjectService readJsonObjectService;


    public ShoppingCartRs shoppingCart(ShoppingCart body) {

        ShoppingCartRs shoppingCartRs = new ShoppingCartRs();

        BigDecimal taxRate = BigDecimal.valueOf(0.0825);

        MathContext mc = new MathContext(2);

        body.getItems()
                .forEach(shoppingCartItem -> {

                    // Get price of item
                    BigDecimal price = shoppingCartItem.getPrice();
                    // Check available discount and get amount of it. Discount cannot be bigger that price
                    BigDecimal discount = checkDiscount(shoppingCartItem.getSku()).min(price);
                    // Get net price after discount
                    BigDecimal priceAfterDiscount = price.subtract(discount);
                    // Temp assign net price after discount to amount with tax
                    BigDecimal priceAfterDiscountWithTax = priceAfterDiscount;
                    // Check if item is taxable
                    if (shoppingCartItem.getIsTaxable()) {
                        // Calculate tax amount
                        BigDecimal tax = priceAfterDiscount.multiply(taxRate, mc);
                        // Add tax to item sum. If no tax, then no add
                        priceAfterDiscountWithTax = priceAfterDiscount.add(tax);
                        // Add to totals
                        shoppingCartRs.addTaxableSubtotalAfterDiscounts(priceAfterDiscount);
                        shoppingCartRs.addTaxTotal(tax);
                    }

                    // Add to totals
                    shoppingCartRs.addSubtotalBeforeDiscounts(price);
                    shoppingCartRs.addDiscountTotal(discount);
                    shoppingCartRs.addSubtotalAfterDiscounts(priceAfterDiscount);
                    shoppingCartRs.addGrandTotal(priceAfterDiscountWithTax);
                });

        return shoppingCartRs;
    }

    private BigDecimal checkDiscount(Long number) {

        BigDecimal discount = BigDecimal.valueOf(0);

        HashMap<Long, BigDecimal> hashMap = readJsonObjectService.readCoupons();
        if (hashMap.containsKey(number)) {
            discount = hashMap.get(number);
        };
        return discount;
    }
}
