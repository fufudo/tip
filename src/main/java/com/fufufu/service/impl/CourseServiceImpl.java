/**
 * @packageName: com.fufufu.service.impl
 * @className: CourseServiceImpl
 * @createTime: 2024-03-31 16:03
 * @description:
 **/

package com.fufufu.service.impl;

import com.fufufu.mapper.CourseMapper;
import com.fufufu.pojo.Article;
import com.fufufu.pojo.Course;
import com.fufufu.pojo.PageBean;
import com.fufufu.service.CourseService;
import com.fufufu.utils.RoleEnum;
import com.fufufu.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Transactional
    @Override
    public PageBean<Course> list(Integer pageNum, Integer pageSize, String name , String no, String teacher) {
        //1.创建PageBean对象
        PageBean<Course> pb = new PageBean<>();

        //2.开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);

        //3.调用mapper
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        String role = (String) map.get("role");
        List<Course> as = null;
        if(RoleEnum.TEACHER.name().equals(role) ){
            as = courseMapper.listByTeacher(userId, role, name , no , teacher);
        } else if (RoleEnum.ADMIN.name().equals(role)) {
            as = courseMapper.listByAdmin(userId, role, name , no , teacher);
        } else if ( RoleEnum.STUDENT.name().equals(role)) {
            role = "TEACHER";
            as = courseMapper.listByStudent(userId, role, name , no , teacher);
        }

        //Page中提供了方法,可以获取PageHelper分页查询后 得到的总记录条数和当前页数据
        Page<Course> p = (Page<Course>) as;
        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Transactional
    @Override
    public void add(Course course) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        String role = (String) map.get("role");
        course.setRole(role);
        course.setCreateUser(userId);
        courseMapper.add(course);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        courseMapper.deleteById(id);
    }

    @Transactional
    @Override
    public void update(Course course) {
        course.setUpdateTime(LocalDateTime.now());
        courseMapper.update(course);
    }
}
 
