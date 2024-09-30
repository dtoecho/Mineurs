package com.newlecmineursprj.service;

import java.util.List;

import com.newlecmineursprj.entity.ProductItem;

public interface ProductItemService {
    List<ProductItem> getList();
    ProductItem getById(Long id);
    List<ProductItem> getByProductId(Long productId);
    ProductItem getByForeignKeys(Long productId, Long sizeId, Long colorId);
    void stockDecrease(int qty, Long id);
}
