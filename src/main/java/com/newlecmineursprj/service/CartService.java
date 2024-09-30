package com.newlecmineursprj.service;

import java.util.List;

import com.newlecmineursprj.entity.Cart;

public interface CartService {

    Cart getById(Long id);
    List<Cart> getByMid(Long mid);
    Cart getByForeignKeys(Long memberId, Long productItemId);
    void update(Cart cart);
    void add(Cart cart);
    void delete(Long deleteId);
    void deleteByMid(Long memberId);
    void increase(Long cartId);
    void decrease(Long cartId);
}
