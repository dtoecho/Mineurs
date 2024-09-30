package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Color;
import com.newlecmineursprj.repository.ColorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository repository;

    @Override
    public List<Color> getList() {
        return repository.findAll();
    }

    @Override
    public Color getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Color> getListByKorName(String query) {
        return repository.findByKorNameContaining(query);
    }

    @Override
    public Long getIdByKorName(String korName) {
        return repository.findIdByKorName(korName);
    }

    @Override
    public void reg(Color color) {
        repository.save(color);
    }

    @Override
    public void deleteAllById(List<Long> colorIds) {
        repository.deleteAllById(colorIds);
    }

}
