package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.Qna;
import com.newlecmineursprj.entity.QnaView;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper
public interface QnaRepository {
    List<Qna> findAll();

    Qna findById(Long id);

    List<Qna> findAllByMemberId(long memberId);

    void save(Qna qna);

    void update(Long id);

    int findByPassword(Long id, String password);

    List<QnaView> findAll(Pageable pageRequest, String searchMethod, String searchKeyword, Integer categoryId,int dueDate);

    long count(String searchMethod, String searchKeyword, int categoryId, int dueDate);

    void edit(Qna qna);

    void delete(long id);
}
