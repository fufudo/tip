/**
 * @packageName: com.fufufu.service.impl
 * @className: GradeServiceImpl
 * @createTime: 2024-04-09 21:59
 * @description:
 **/

package com.fufufu.service.impl;

import com.fufufu.mapper.CourseMapper;
import com.fufufu.mapper.GradeMapper;
import com.fufufu.pojo.Grade;
import com.fufufu.pojo.PageBean;
import com.fufufu.pojo.Result;
import com.fufufu.service.GradeService;
import com.fufufu.utils.RoleEnum;
import com.fufufu.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;
import java.util.Map;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private CourseMapper courseMapper;


    @Override
    public void insert(Grade grade) {
        Grade grade1 = gradeMapper.selectByCondition(grade.getCourseId(),grade.getStudentId());
        if(grade1 == null){
            gradeMapper.insert(grade);
        }
    }

    @Override
    public PageBean<Grade> list(Integer pageNum, Integer pageSize, Grade grade) {
        PageBean<Grade> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        String role = (String) map.get("role");
        List<Grade> as = null;

        if(RoleEnum.TEACHER.name().equals(role)){
            grade.setTeacherId(userId);
            as = gradeMapper.selectByTeacher(grade);
        }else if(RoleEnum.STUDENT.name().equals(role)){
            grade.setStudentId(userId);
            as = gradeMapper.selectByStudent(grade);
        }else {
            as = gradeMapper.selectAll(grade);
        }
        Page<Grade> p = (Page<Grade>) as;
        pb.setItems(p.getResult());
        pb.setTotal(p.getTotal());
        return pb;
    }

    @Override
    public void update(Grade grade) {
        gradeMapper.update(grade);
    }

    @Override
    public void deleteById(Integer id) {
        gradeMapper.deleteById(id);
    }

    @Override
    public void uploadCourse(Grade grade) {
         gradeMapper.uploadCourse(grade);

    }

    @Override
    public Grade selectByCondition(Grade grade) {
        Grade grade1 = gradeMapper.selectByCondition(grade.getCourseId(),grade.getStudentId());
        return grade1;
    }




}
 
