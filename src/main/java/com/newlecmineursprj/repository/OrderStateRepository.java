package com.newlecmineursprj.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.newlecmineursprj.entity.OrderState;

@Mapper
public interface OrderStateRepository {
    
    List<OrderState> findAll();

    OrderState findByName(String name);
}
