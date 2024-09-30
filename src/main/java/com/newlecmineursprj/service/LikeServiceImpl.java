package com.newlecmineursprj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecmineursprj.entity.Like;
import com.newlecmineursprj.repository.LikeRepository;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository repository;

    @Override
    public Like getByForeignKeys(Long memberId, Long productId) {
        return repository.findByForeignKeys(memberId, productId);
    }

    @Override
    public void add(Like like) {
        repository.add(like);
    }

    
    
}
