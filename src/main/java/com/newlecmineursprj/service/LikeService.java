package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Like;

public interface LikeService {
    
    Like getByForeignKeys(Long memberId, Long productId);
    void add(Like like);

}
