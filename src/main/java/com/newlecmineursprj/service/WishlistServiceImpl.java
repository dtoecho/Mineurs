package com.newlecmineursprj.service;

import org.springframework.stereotype.Service;

import com.newlecmineursprj.repository.LikeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final LikeRepository repository;

    @Override
    public void delete(Long memberId, Long productId) {
        repository.delete(memberId, productId);
    }

}