package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Qna;
import com.newlecmineursprj.entity.QnaView;
import com.newlecmineursprj.repository.QnaRepository;
import com.newlecmineursprj.util.CustomPageImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QnaServiceimpl implements QnaService {

    @Autowired
    private final QnaRepository repository;

    @Override
    public List<Qna> getList() {
        return repository.findAll();
    }

    @Override
    public void reg(Qna qna) {
        repository.save(qna);
    }

    @Override
    public Qna getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Qna> getListByMemberId(long memberId) {
        return repository.findAllByMemberId(memberId);
    }

    @Override
    public void increase(Long id) {
        repository.update(id);
    }

    @Override
    public int getByPassword(Long id, String password) {
        return repository.findByPassword(id, password);
    }

    @Override
    public CustomPageImpl<QnaView> getList(int pageNumber, int pageSize, int pageGroupSize, String searchMethod, String searchKeyword, int categoryId, int dueDate) {

        Pageable pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        List<QnaView> content = repository.findAll(pageRequest, searchMethod, searchKeyword, categoryId,dueDate);
        long count = repository.count(searchMethod, searchKeyword, categoryId,dueDate);
        return new CustomPageImpl<QnaView>(content,pageRequest,count,pageGroupSize);
    }

    @Override
    public void edit(Qna qna) {
        repository.edit(qna);
    }

    @Override
    public void delete(long id) {
        repository.delete(id);
    }


//    @Override
//    public int getByPassword(Long id, String password) {
//        int result = repository.findByPassword(id,password);
////        if(qna.getPassword().equals(password))
////            return 0;
//    }

}
