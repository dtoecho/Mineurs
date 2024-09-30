package com.newlecmineursprj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecmineursprj.entity.Coupon;
import com.newlecmineursprj.repository.CouponRepository;

@Service
public class CouponServiceimpl implements CouponService {

    @Autowired
    private CouponRepository repository;

    @Override
    public List<Coupon> getList() {
        return repository.findAll();
    }

    @Override
    public Coupon getById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Coupon> getListByMemberId(long id) {
        return repository.findAllByMemberId(id);
    }

    @Override
    public List<Coupon> getValidByMemberId(long id) {
        return repository.findValidByMemberId(id);
    }

    @Override
    public List<Coupon> getInvalidByMemberId(long id) {
       return repository.findInvalidByMemberId(id);
    }

    
    
}
