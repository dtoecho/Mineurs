package com.newlecmineursprj.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Point {

    private Long id;
    private long accumulatePoint;
    private Date regDate;
    private String contents;
    private Long memberId;
    private Long orderId;
}
