package com.newlecmineursprj.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecmineursprj.entity.Category;
import com.newlecmineursprj.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    @Override
    public List<Category> getList() {
        return repository.findAll();
    }

    @Override
    public Category getById(long id) {
        return repository.findById(id);
    }


}
