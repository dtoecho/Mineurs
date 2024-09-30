package com.newlecmineursprj.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.newlecmineursprj.entity.ProductItem;

@Mapper
public interface ProductItemRepository {
    List<ProductItem> findAll();
    ProductItem findById(Long id);
    List<ProductItem> findByProductId(Long productId);
    ProductItem findByForeignKeys(Long productId, Long sizeId, Long colorId);
    void stockDecrease(int qty, Long id);

    void saveAll(List<ProductItem> productItems);
}
