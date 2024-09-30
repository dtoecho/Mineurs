package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.Color;
import com.newlecmineursprj.entity.ProductItem;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ColorRepository {

    List<Color> findAll();
    Color findById(Long id);

    List<Color> findByKorNameContaining(String query);
    Long findIdByKorName(String korName);

    void save(Color color);

    void deleteAllById(List<Long> colorIds);
}
