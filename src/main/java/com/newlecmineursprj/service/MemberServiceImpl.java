package com.newlecmineursprj.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.newlecmineursprj.entity.Member;
import com.newlecmineursprj.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final PasswordEncoder encoder;

    private final MemberRepository repository;

    @Override
    public List<Member> getList(Integer page) {
        return getList(page, null, null);
    }

    @Override
    public List<Member> getList(Integer page, String searchMethod, String searchKeyword) {

        int size = 10;
        int offset = (page - 1) * size;

        return repository.findAll(searchMethod, searchKeyword, offset, size);
    }

    @Override
    public Member getById(long id) {
        return repository.findById(id);
    }

    @Override
    public Member getByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void save(Member member) {
        repository.save(member);
    }

    @Override
    public void update(Member member) {

        String Encoded = encoder.encode(member.getPassword());

        member.setPassword(Encoded);

        repository.update(member);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll(List<Long> ids) {
        repository.deleteAll(ids);
    }

    @Override
    public int getCount() {
        return getCount(null, null);
    }

    @Override
    public int getCount(String searchMethod, String searchKeyword) {
        return repository.count(searchMethod, searchKeyword);
    }

}
