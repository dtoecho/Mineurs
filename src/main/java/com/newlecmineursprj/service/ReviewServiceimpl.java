package com.newlecmineursprj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecmineursprj.entity.Review;
import com.newlecmineursprj.repository.ReviewRepository;

@Service
public class ReviewServiceimpl implements ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Override
    public List<Review> getList() {
        return repository.findAll();
    }

    @Override
    public Review getById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Review> getListByMemberId(long memberId) {
        return repository.findAllByMemberId(memberId);
    }
    
}
