package com.fufufu.service;

import com.fufufu.pojo.Grade;
import com.fufufu.pojo.PageBean;
import com.fufufu.pojo.Result;

public interface GradeService {

    void insert(Grade grade);

    PageBean<Grade> list(Integer pageNum, Integer pageSize, Grade grade);

    void update(Grade grade);

    void deleteById(Integer id);

    void uploadCourse(Grade grade);

    Grade selectByCondition(Grade grade);
}
