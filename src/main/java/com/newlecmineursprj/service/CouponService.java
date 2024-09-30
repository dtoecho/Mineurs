package com.newlecmineursprj.service;

import java.util.List;

import com.newlecmineursprj.entity.Coupon;

public interface CouponService {
    List<Coupon> getList();
    Coupon getById(long id);
    List<Coupon> getListByMemberId(long id);
    List<Coupon> getValidByMemberId(long id);
    List<Coupon> getInvalidByMemberId(long id);
}
