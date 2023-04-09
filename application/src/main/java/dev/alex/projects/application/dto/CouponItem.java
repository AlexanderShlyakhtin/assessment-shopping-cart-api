package dev.alex.projects.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponItem {

    private String couponName;
    private Long appliedSku;
    private BigDecimal discountPrice;
}
