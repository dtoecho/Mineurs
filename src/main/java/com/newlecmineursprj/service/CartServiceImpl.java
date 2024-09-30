package com.newlecmineursprj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecmineursprj.entity.Cart;
import com.newlecmineursprj.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {
    
    @Autowired
    private CartRepository repository;

    @Override
    public List<Cart> getByMid(Long mid) {
        return repository.findByMid(mid);
    }

    @Override
    public Cart getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Cart getByForeignKeys(Long memberId, Long productItemId) {
        return repository.findByForeignKeys(memberId, productItemId);
    }

    @Override
    public void update(Cart cart) {
        repository.update(cart);
    }

    @Override
    public void add(Cart cart) {
        repository.add(cart);
    }

    @Override
    public void delete(Long deleteId) {
        repository.delete(deleteId);
    }

    @Override
    public void deleteByMid(Long memberId) {
        repository.deleteByMid(memberId);
    }

    @Override
    public void increase(Long cartId) {
        repository.increase(cartId);
    }

    @Override
    public void decrease(Long cartId) {
        repository.decrease(cartId);
    }

    

    


}
