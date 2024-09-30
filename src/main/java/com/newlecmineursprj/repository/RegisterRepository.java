package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterRepository {
    int save(Member member);

    Member findById(String email);
}
