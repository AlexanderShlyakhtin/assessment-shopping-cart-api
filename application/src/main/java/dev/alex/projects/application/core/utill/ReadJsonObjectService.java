package dev.alex.projects.application.core.utill;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alex.projects.application.dto.CouponsList;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;

@Component
public class ReadJsonObjectService {

    public HashMap readCoupons() {

        CouponsList couponsList = new CouponsList();
        HashMap<Long, BigDecimal> couponHashMap = new HashMap<Long, BigDecimal>();

        ObjectMapper objectMapper = new ObjectMapper();
        InputStream is = CouponsList.class.getResourceAsStream("/coupons.json");
        try {
            couponsList = objectMapper.readValue(is, CouponsList.class);
            couponsList.getCoupons().forEach(couponItem -> {
                couponHashMap.put(couponItem.getAppliedSku(), couponItem.getDiscountPrice());
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return couponHashMap;
    }
}
