package com.newlecmineursprj.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointView {

    private Long point;
    private Date regDate;
    private String contents;
    private Long memberId;
    private String name;
    private String code;
    private Long count;
    private String productNames;
}
