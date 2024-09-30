package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Qna;
import com.newlecmineursprj.entity.QnaView;
import com.newlecmineursprj.util.CustomPageImpl;

import java.util.List;

public interface QnaService {
    List<Qna> getList();
    void reg(Qna qna);
    Qna getById(Long id);
    List<Qna> getListByMemberId(long memberId);

    void increase(Long id);

    int getByPassword(Long id, String password);

    CustomPageImpl<QnaView> getList(int pageNumber, int pageSize, int pageGroupSize, String searchMethod, String searchKeyword, int categoryId, int dueDate);

    void edit(Qna qna);

    void delete(long id);
}
