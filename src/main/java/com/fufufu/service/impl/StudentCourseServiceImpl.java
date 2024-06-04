/**
 * @packageName: com.fufufu.service.impl
 * @className: StudentCourseServiceImpl
 * @createTime: 2024-04-07 14:32
 * @description:
 **/

package com.fufufu.service.impl;

import com.fufufu.mapper.StudentCourseMapper;
import com.fufufu.pojo.PageBean;
import com.fufufu.pojo.Student;
import com.fufufu.pojo.StudentCourse;
import com.fufufu.service.StudentCourseService;
import com.fufufu.utils.RoleEnum;
import com.fufufu.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    @Autowired
    private StudentCourseMapper studentCourseMapper;



    @Override
    public void add(StudentCourse studentCourse) {

        studentCourseMapper.add(studentCourse);
    }

    @Override
    public StudentCourse selectByCondition(Integer courseId, Integer studentId) {

        return studentCourseMapper.selectByCondition(courseId, studentId);
    }

    @Override
    public PageBean<StudentCourse> list(Integer pageNum, Integer pageSize, StudentCourse studentCourse) {
        //1.创建PageBean对象
        PageBean<StudentCourse> pb = new PageBean<>();
        //2.开启分页查询 PageHelper
        PageHelper.startPage(pageNum,pageSize);
        //3.调用mapper
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        String role = (String) map.get("role");

        List<StudentCourse> studentList = null;
        if(RoleEnum.STUDENT.name().equals(role)){
            studentCourse.setStudentId(userId);
            studentList = studentCourseMapper.selectAll(studentCourse);
        } else if (RoleEnum.ADMIN.name().equals(role)) {
            studentList = studentCourseMapper.selectAllByAdmin(studentCourse);
        }

        //Page中提供了方法,可以获取PageHelper分页查询后 得到的总记录条数和当前页数据
        Page<StudentCourse> p = (Page<StudentCourse>) studentList;
        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public void deteleById(Integer id) {
        studentCourseMapper.deteleById(id);
    }

    @Override
    public List<StudentCourse> isSelected() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        return studentCourseMapper.isSelected(userId);
    }
}
 
