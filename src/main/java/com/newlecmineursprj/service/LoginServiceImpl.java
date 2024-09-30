package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Member;
import com.newlecmineursprj.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final LoginRepository repository;

    @Override
    public Member getById(Member member) {

        Member result = repository.findById(member);

        return result;
    }

    @Override
    public void updatePassword(Member member,String password) {
        member.setEncodedPassword(password);
        repository.update(member);
    }
}
