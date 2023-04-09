package dev.alex.projects.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartItem {

    private String itemName;
    private Long sku;
    private Boolean isTaxable;
    private Boolean ownBrand;
    private BigDecimal price;
}
