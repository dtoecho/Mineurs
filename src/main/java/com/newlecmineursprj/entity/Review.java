package com.newlecmineursprj.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class Review {
    private Long id;
    private String content;
    private Date regDatetime;
}
