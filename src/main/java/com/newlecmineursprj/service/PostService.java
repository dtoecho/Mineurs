package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Board;
import com.newlecmineursprj.entity.PostView;

import java.util.List;

public interface PostService {

    List<PostView> getList(Integer page, String searchMethod, String searchKeyword, Integer boardId, String qnaCategory);

    PostView getById(Long id);

    int getCount(Integer page, String searchMethod,String searchKeyword,Integer boardId,String qnaCategory);
}
