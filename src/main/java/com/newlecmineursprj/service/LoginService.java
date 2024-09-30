package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Member;

public interface LoginService {
    Member getById(Member member);

    void updatePassword(Member member, String password);
}
