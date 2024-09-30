package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.Order;
import com.newlecmineursprj.entity.OrderView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper
public interface OrderRepository {

    List<OrderView> findAll(@Param("pageRequest") Pageable pageRequest,
                            @Param("searchMethod") String searchMethod, @Param("searchKeyword") String searchKeyword,
                            @Param("memberId") Long memberId, @Param("calendarStart") String calendarStart,
                            @Param("calendarEnd") String calendarEnd, @Param("startDate") String startDate);

    int getCount(String searchMethod, String searchKeyword, Long memberId);

    OrderView findById(Long id);
    List<OrderView> findByMemberId(Long memberId);
    void add(Order order);
}
