package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginRepository {
    Member findById(Member member);

    void update(Member member);
}
