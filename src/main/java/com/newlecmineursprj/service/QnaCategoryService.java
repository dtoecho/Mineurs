package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.QnaCategory;

import java.util.List;

public interface QnaCategoryService {
    List<QnaCategory> getList();

    QnaCategory getById(Long categoryId);
}
