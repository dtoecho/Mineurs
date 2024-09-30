package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductView;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper
public interface ProductRepository {
    List<ProductView> findAll(@Param("pageRequest") Pageable pageRequest, @Param("searchMethod") String searchMethod,
                              @Param("searchKeyword") String searchKeyword, @Param("categoryId") Long categoryId,
                              @Param("startDate") String startDate, @Param("calendarStart") String calendarStart, @Param("calendarEnd") String calendarEnd,
                              @Param("displayStatusResult") Integer displayStatusResult,@Param("sellStatusResult") Integer sellStatusResult, @Param("memberId") Long memberId);

    void reg(Product product);

    Product findById(Long id);

    void updateById(Product product);

    void deleteAll(List<Long> deleteId);

    int getCount(String searchMethod, String searchKeyword, Long categoryId);

    List<ProductView> findAllByMemberId(@Param("pageRequest") Pageable pageRequest, @Param("memberId") long memberId);

    long getCountByMemberId(long memberId);

    int updateAll(List<Product> products);
}
