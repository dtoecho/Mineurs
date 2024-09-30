package com.newlecmineursprj.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QnaView {

    private Long id;
    private String mainImgPath;
    private String title;
    private String userName;
    private Date regDateTime;
    private Long views;
    private String categoryName;
    private Long state;
}
