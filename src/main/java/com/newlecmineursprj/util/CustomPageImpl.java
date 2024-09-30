package com.newlecmineursprj.util;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CustomPageImpl<T> extends PageImpl<T> {
    private final long pageGroupSize;
    public CustomPageImpl(List<T> content, Pageable pageable, long total, long pageGroupSize) {
        super(content, pageable, total);
        this.pageGroupSize = pageGroupSize;
    }

    public CustomPageImpl(List<T> content, long pageGroupSize) {
        super(content);
        this.pageGroupSize = pageGroupSize;
    }

    public boolean hasPrevPageGroup(){
        return getPageGroupStartNumber() - 1 > 0;
    }
    public boolean hasNextPageGroup(){
        return getPageGroupEndNumber() < getTotalPages();
    }

    public long getPageGroupStartNumber(){
        return (getNumber() / this.pageGroupSize) * this.pageGroupSize + 1;
    }
    public long getPageGroupEndNumber(){
        long endNum = (getNumber() / this.pageGroupSize + 1) * this.pageGroupSize;
        int totalPages = getTotalPages();
        return endNum > totalPages ? totalPages : endNum;
    }
}
