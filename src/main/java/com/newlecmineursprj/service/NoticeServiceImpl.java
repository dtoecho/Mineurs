package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Notice;
import com.newlecmineursprj.entity.SearchMethod;
import com.newlecmineursprj.repository.NoticeRepository;
import com.newlecmineursprj.repository.NoticeSearchMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository repository;
    private final NoticeSearchMethodRepository noticeSearchMethodRepository;
    @Override
    public List<Notice> findAll() {
        return repository.findAll();
    }

    @Override
    public Notice findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void reg(Notice notice, Long memberId) {
        repository.reg(notice, memberId);
    }

    @Override
    public void deleteAll(List<Long> noticeIds) {
        repository.deleteAll(noticeIds);
    }

    @Override
    public void update(Notice notice, Long id) {
        repository.update(notice, id);
    }

    @Override
    public List<SearchMethod> findAllSearchMethods() {
        return noticeSearchMethodRepository.findAll();
    }
}
