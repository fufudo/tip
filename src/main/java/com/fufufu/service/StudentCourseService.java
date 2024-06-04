package com.fufufu.service;

import com.fufufu.pojo.PageBean;
import com.fufufu.pojo.StudentCourse;

import java.util.List;

public interface StudentCourseService {
    void add(StudentCourse studentCourse);

    StudentCourse selectByCondition(Integer courseId , Integer studentId);

    PageBean<StudentCourse> list(Integer pageNum, Integer pageSize, StudentCourse studentCourse);

    void deteleById(Integer id);

    List<StudentCourse> isSelected();
}
