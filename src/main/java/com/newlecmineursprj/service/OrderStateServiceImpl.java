package com.newlecmineursprj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecmineursprj.entity.OrderState;
import com.newlecmineursprj.repository.OrderStateRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderStateServiceImpl implements OrderStateService {

    @Autowired
    private OrderStateRepository repository;

    @Override
    public List<OrderState> getList() {
        return repository.findAll();
    }

    @Override
    public OrderState getByName(String name) {
        return repository.findByName(name);
    }
    
}
