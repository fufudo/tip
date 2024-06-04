package com.fufufu.service;

import com.fufufu.pojo.Article;
import com.fufufu.pojo.Course;
import com.fufufu.pojo.PageBean;

public interface CourseService {

    PageBean<Course> list(Integer pageNum, Integer pageSize, String name , String no, String teacher);

    void add(Course course);

    void deleteById(Integer id);

    void update(Course course);
}
