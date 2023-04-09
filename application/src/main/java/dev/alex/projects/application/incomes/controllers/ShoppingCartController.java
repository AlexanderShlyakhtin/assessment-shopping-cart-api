package dev.alex.projects.application.incomes.controllers;

import dev.alex.projects.application.dto.ShoppingCart;
import dev.alex.projects.application.dto.ShoppingCartRs;
import dev.alex.projects.application.core.services.ShoppingCartService;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping-cart")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final ObservationRegistry observationRegistry;

    @PostMapping("/calculate")
    @ResponseStatus(HttpStatus.OK)
    public ShoppingCartRs shoppingCart(@RequestBody ShoppingCart body) {
        return Observation
                .createNotStarted("calculateShoppingCart", this.observationRegistry)
                .observe(() -> shoppingCartService.shoppingCart(body));
    }
}
