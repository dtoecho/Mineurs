package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberRepository {

    List<Member> findAll(String searchMethod,String searchKeyword,int offset,int size);

    Member findById(long id);
    Optional<Member> findByUsername(String username);
    
    Member findByName(String name);

    void save(Member member);

    void update(Member member);

    void deleteById(long id);

    void deleteAll(List<Long> ids);

    int count(String searchMethod, String searchKeyword);

    Member findByEmail(String email);

    void updatePoint(Integer point,Long memberId);
}
