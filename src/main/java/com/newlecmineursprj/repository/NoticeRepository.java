package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeRepository {

    List<Notice> findAll();

    Notice findById(Long id);

    void reg(@Param("notice") Notice notice, @Param("memberId") Long memberId);

    void deleteAll(List<Long> noticeIds);

    void update(@Param("notice")Notice notice, @Param("memberId") Long id);
}
