package com.newlecmineursprj.repository.admin;

import com.newlecmineursprj.entity.QnaCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaCategoryRepository {
    List<QnaCategory> findAll();

    QnaCategory findById(Long categoryId);
}
