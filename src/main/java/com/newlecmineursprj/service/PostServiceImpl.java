package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.PostView;
import com.newlecmineursprj.repository.admin.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository repository;

    @Override
    public List<PostView> getList(Integer page, String searchMethod, String searchKeyword, Integer boardId, String qnaCategory) {
        int size = 10;
        int offset = (page - 1) * size;

        return repository.findAll(page,searchMethod,searchKeyword,boardId,qnaCategory);
    }

    @Override
    public PostView getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public int getCount(Integer page, String searchMethod, String searchKeyword, Integer boardId, String qnaCategory) {
        return repository.count(page,searchMethod,searchKeyword,boardId,qnaCategory);
    }
}
