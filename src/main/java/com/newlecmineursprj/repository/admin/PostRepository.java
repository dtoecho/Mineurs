package com.newlecmineursprj.repository.admin;

import com.newlecmineursprj.entity.Board;
import com.newlecmineursprj.entity.PostView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostRepository {

    List<PostView> findAll(Integer page, String searchMethod, String searchKeyword, Integer boardId, String qnaCategory);

    PostView findById(Long id);

    int count(Integer page, String searchMethod,String searchKeyword,Integer boardId,String qnaCategory);
}
