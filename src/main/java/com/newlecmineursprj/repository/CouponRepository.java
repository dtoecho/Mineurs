package com.newlecmineursprj.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.newlecmineursprj.entity.Coupon;

@Mapper
public interface CouponRepository {
    List<Coupon> findAll();
    Coupon findById(long id);
    List<Coupon> findAllByMemberId(long id);
    List<Coupon> findValidByMemberId(long id);
    List<Coupon> findInvalidByMemberId(long id);
}
