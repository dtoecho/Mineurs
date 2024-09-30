package com.newlecmineursprj.repository;

import java.util.List;

import com.newlecmineursprj.entity.Point;
import org.apache.ibatis.annotations.Mapper;

import com.newlecmineursprj.entity.PointView;

@Mapper
public interface PointRepository {

    List<PointView> findById(Long memberId);

    void save(Integer point, Long memberId, Long id);


}
