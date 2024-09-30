package com.newlecmineursprj.service;

import java.util.List;

import com.newlecmineursprj.entity.Review;

public interface ReviewService {
    List<Review> getList();
    Review getById(long id);
    List<Review> getListByMemberId(long memberId);
}
