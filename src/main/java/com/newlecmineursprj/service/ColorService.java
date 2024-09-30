package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Color;

import java.util.List;

public interface ColorService {
    List<Color> getList();
    Color getById(Long id);

    List<Color> getListByKorName(String query);
    Long getIdByKorName(String korName);

    void reg(Color color);

    void deleteAllById(List<Long> colorIds);
}
