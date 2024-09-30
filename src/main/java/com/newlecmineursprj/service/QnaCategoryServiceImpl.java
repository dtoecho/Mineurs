package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.QnaCategory;
import com.newlecmineursprj.repository.admin.QnaCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QnaCategoryServiceImpl implements QnaCategoryService{

    private final QnaCategoryRepository repository;

    @Override
    public List<QnaCategory> getList() {
        return repository.findAll();
    }

    @Override
    public QnaCategory getById(Long categoryId) {
        return repository.findById(categoryId);
    }
}
