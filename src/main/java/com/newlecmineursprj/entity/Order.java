package com.newlecmineursprj.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Objects;

import org.springframework.format.annotation.NumberFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    private Long id;
    private Long memberId;
    private Long productId;
    private String code;
    private Date orderedDatetime;
    private Integer totalProductPrice;
    private Integer totalDeliveryFee;
    private Integer totalDiscountAmount;
    private Long deliveryInfoId;
    private Long couponId;
    private Long paymentMethodId;
}
