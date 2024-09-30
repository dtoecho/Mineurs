package com.newlecmineursprj.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.newlecmineursprj.entity.Review;

@Mapper
public interface ReviewRepository {
    List<Review> findAll();
    Review findById(long id);
    List<Review> findAllByMemberId(long memberId);
}
