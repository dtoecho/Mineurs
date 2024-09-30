package com.newlecmineursprj.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.newlecmineursprj.entity.OrderItem;

@Mapper
public interface OrderItemRepository {
    
    List<OrderItem> findAll();
    OrderItem findById(Long id);
    void add(OrderItem orderItem);
    List<OrderItem> findByOrderId(Long orderId);
    void updateOrderState(Long orderStateId,Long orderItemId);

}
