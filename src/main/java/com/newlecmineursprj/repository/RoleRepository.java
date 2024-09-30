package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleRepository {
    List<Role> findAllByMemberId(Long memberId);
}
