package com.newlecmineursprj.service;

import java.util.List;

import com.newlecmineursprj.entity.OrderState;

/**
 * OrderStateService
 */
public interface OrderStateService {
    List<OrderState> getList();
    OrderState getByName(String name);
}