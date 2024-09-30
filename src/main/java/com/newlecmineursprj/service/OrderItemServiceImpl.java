package com.newlecmineursprj.service;

import java.util.List;

import com.newlecmineursprj.entity.OrderItem;
import org.springframework.stereotype.Service;

import com.newlecmineursprj.repository.OrderItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository repository;

    @Override
    public List<OrderItem> getList() {
        return repository.findAll();
    }

    @Override
    public OrderItem getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void add(OrderItem orderItem) {
        repository.add(orderItem);
    }

    @Override
    public List<OrderItem> getByOrderId(Long orderId) {
        return repository.findByOrderId(orderId);
    }

    @Override
    public void changeOrderState(Long orderStateId, Long orderItemId) {
        repository.updateOrderState(orderStateId, orderItemId);
    }

    

}
