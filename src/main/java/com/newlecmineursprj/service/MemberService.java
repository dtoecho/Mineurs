package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Member;

import java.util.List;

public interface MemberService {

    List<Member> getList(Integer page);

    List<Member> getList(Integer page, String searchMethod, String searchKeyword);
    
    Member getById(long id);

    Member getByName(String name);

    void save(Member member);

    void update(Member member);

    void deleteById(long id);

    void deleteAll(List<Long> ids);

    int getCount();

    int getCount(String searchMethod,String searchKeyword);
}
