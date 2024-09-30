package com.newlecmineursprj.entity;

import lombok.Data;

@Data
public class ProductItem {
    private Long id;
    private int qty;
    private String code;
    private Long productId;
    private Long sizeId;
    private Long colorId;
}
