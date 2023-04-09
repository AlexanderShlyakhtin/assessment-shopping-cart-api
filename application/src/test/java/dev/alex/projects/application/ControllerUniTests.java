package dev.alex.projects.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alex.projects.application.dto.ShoppingCart;
import dev.alex.projects.application.dto.ShoppingCartRs;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@ContextConfiguration(classes = TestApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ControllerUniTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Nested
    @DisplayName("Controller Unit Tests: positive")
    class ShoppingCartUnitTests {

        @Nested
        @DisplayName("General Tests")
        class GeneralTests {

            @Test
            @DisplayName("Calculate of shopping cart totals")
            public void calculateTotals() throws Exception {

                // given
                ShoppingCart shoppingCart = Helper.readRequestJson();

                //when
                ResultActions resultActions = mockMvc.perform(post("/shopping-cart/calculate")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(shoppingCart)))
                        .andDo(MockMvcResultHandlers.print());
                // then
                MvcResult mvcResult = resultActions
                        .andExpect(status().is2xxSuccessful())
                        .andReturn();

                ShoppingCartRs shoppingCartRs = objectMapper
                        .readValue(mvcResult.getResponse().getContentAsString(), ShoppingCartRs.class);
                Assertions.assertEquals(BigDecimal.valueOf(221.41), shoppingCartRs.getSubtotalBeforeDiscounts());
                Assertions.assertEquals(BigDecimal.valueOf(3.63), shoppingCartRs.getDiscountTotal());
                Assertions.assertEquals(BigDecimal.valueOf(217.78), shoppingCartRs.getSubtotalAfterDiscounts());
                Assertions.assertEquals(BigDecimal.valueOf(130.50).setScale(2), shoppingCartRs.getTaxableSubtotalAfterDiscounts());
                Assertions.assertEquals(BigDecimal.valueOf(10.77), shoppingCartRs.getTaxTotal());
                Assertions.assertEquals(BigDecimal.valueOf(228.55), shoppingCartRs.getGrandTotal());

            }

            @Test
            @DisplayName("Calculate of shopping cart totals with empty request")
            public void calculateTotalsWithEmptyRequest() throws Exception {

                // given
                ShoppingCart shoppingCart = Helper.readRequestJsonWithEmptyRequest();

                //when
                ResultActions resultActions = mockMvc.perform(post("/shopping-cart/calculate")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(shoppingCart)))
                        .andDo(MockMvcResultHandlers.print());
                // then
                MvcResult mvcResult = resultActions
                        .andExpect(status().is2xxSuccessful())
                        .andReturn();

                ShoppingCartRs shoppingCartRs = objectMapper
                        .readValue(mvcResult.getResponse().getContentAsString(), ShoppingCartRs.class);
                Assertions.assertEquals(BigDecimal.valueOf(0).setScale(2), shoppingCartRs.getSubtotalBeforeDiscounts());
                Assertions.assertEquals(BigDecimal.valueOf(0).setScale(2), shoppingCartRs.getDiscountTotal());
                Assertions.assertEquals(BigDecimal.valueOf(0).setScale(2), shoppingCartRs.getSubtotalAfterDiscounts());
                Assertions.assertEquals(BigDecimal.valueOf(0).setScale(2), shoppingCartRs.getTaxableSubtotalAfterDiscounts());
                Assertions.assertEquals(BigDecimal.valueOf(0).setScale(2), shoppingCartRs.getTaxTotal());
                Assertions.assertEquals(BigDecimal.valueOf(0).setScale(2), shoppingCartRs.getGrandTotal());

            }
        }


    }
}

