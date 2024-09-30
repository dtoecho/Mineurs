package com.newlecmineursprj.entity;

import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostView {

    private Long postId;
    private String boardName;
    private Long boardId;
    private String title;
    private String content;
    private Long state;
    private String memberName;
    private Date regDateTime;
    private Long id;
    private Long memberId;
}
