package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Category;
import com.newlecmineursprj.entity.QnaCategory;

import java.util.List;

public interface CategoryService {
    List<Category> getList();
    Category getById(long id);

}
