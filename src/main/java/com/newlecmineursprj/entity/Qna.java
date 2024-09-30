package com.newlecmineursprj.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Qna {
    private Long id;
    private String content;
    private Date regDatetime;
    private Long views;
    private Long qnaCategoryId;
    private Long memberId;
    private Long boardId;
    private String title;
    private String categoryName;
    private String password;
    private String answerContent;
    private Long state;

}
