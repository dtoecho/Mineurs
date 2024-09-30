package com.newlecmineursprj.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.newlecmineursprj.entity.ProductSubImg;

@Mapper
public interface ProductSubImgRepository {
    void reg(List<ProductSubImg> productSubImgs);
    List<ProductSubImg> findAll(Long productId);
    void updatedImgs(List<ProductSubImg> updateProductSubImgs);
    void deleteAll(List<ProductSubImg> updateSubImgs);
}
