package com.newlecmineursprj.dto;

import lombok.Data;

@Data
public class ProductQtyDTO {
    private int qty;
    private Long colorId;
    private String color;
    private Long sizeId;
    private String size;
}
