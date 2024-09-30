package com.newlecmineursprj.repository;

import org.apache.ibatis.annotations.Mapper;

import com.newlecmineursprj.entity.Like;

@Mapper
public interface LikeRepository {

    Like findByForeignKeys(Long memberId, Long productId);

    void add(Like like);

    void delete(Long memberId, Long productId);

}
