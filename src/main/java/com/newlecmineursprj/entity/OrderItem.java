package com.newlecmineursprj.entity;

import lombok.Data;

@Data
public class OrderItem {
    private Long id;
    private Integer qty;
    private Integer totalPrice;
    private Long orderId;
    private Long orderStateId;
    private Long productItemId;
}
