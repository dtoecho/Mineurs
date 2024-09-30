package com.newlecmineursprj.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.newlecmineursprj.entity.Category;

@Mapper
public interface CategoryRepository {
    List<Category> findAll();

    Category findById(long id);

}
