package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.SearchMethod;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeSearchMethodRepository {

    List<SearchMethod> findAll();
}
