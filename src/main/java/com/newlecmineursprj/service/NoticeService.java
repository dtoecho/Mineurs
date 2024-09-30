package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Notice;
import com.newlecmineursprj.entity.SearchMethod;

import java.util.List;

public interface NoticeService {
    List<Notice> findAll();

    Notice findById(Long id);

    void reg(Notice notice, Long memberId);

    void deleteAll(List<Long> noticeIds);

    void update(Notice notice, Long id);

    List<SearchMethod> findAllSearchMethods();
}
