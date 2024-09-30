package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.MemberRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRoleRepository {

    void save(MemberRole memberRole);
}
