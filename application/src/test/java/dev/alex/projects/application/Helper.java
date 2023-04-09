package dev.alex.projects.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alex.projects.application.dto.CouponsList;
import dev.alex.projects.application.dto.ShoppingCart;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;

public class Helper {

    public static ShoppingCart readRequestJson() {

        ShoppingCart shoppingCart = new ShoppingCart();

        ObjectMapper objectMapper = new ObjectMapper();
        InputStream is = CouponsList.class.getResourceAsStream("/cart.json");
        try {
            shoppingCart = objectMapper.readValue(is, ShoppingCart.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return shoppingCart;

    }

    public static ShoppingCart readRequestJsonWithEmptyRequest() {

        ShoppingCart shoppingCart = new ShoppingCart();

        ObjectMapper objectMapper = new ObjectMapper();
        InputStream is = CouponsList.class.getResourceAsStream("/cart1.json");
        try {
            shoppingCart = objectMapper.readValue(is, ShoppingCart.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return shoppingCart;

    }

}
