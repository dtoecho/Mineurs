package com.newlecmineursprj.service;

import java.util.List;

import com.newlecmineursprj.entity.Point;
import com.newlecmineursprj.entity.PointView;

public interface PointService {

    List<PointView> getList(Long memberId);

}
