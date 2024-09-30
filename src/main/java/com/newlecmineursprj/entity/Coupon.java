package com.newlecmineursprj.entity;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coupon {
    private long id;
    private String name;
    private String description;
    private double discountRate; 
    private Date validDateStart;
    private Date validDateEnd;
    private int minimumPurchase;
    private Boolean used;
    private Timestamp orderedDatetime;
}
