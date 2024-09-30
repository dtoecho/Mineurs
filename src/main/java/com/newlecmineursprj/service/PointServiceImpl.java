package com.newlecmineursprj.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.newlecmineursprj.entity.Point;
import com.newlecmineursprj.entity.PointView;
import com.newlecmineursprj.repository.PointRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {

    private final PointRepository repository;

    @Override
    public List<PointView> getList(Long memberId) {

        return repository.findById(memberId);
    }



}
