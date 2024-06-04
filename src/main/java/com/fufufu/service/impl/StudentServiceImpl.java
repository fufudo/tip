/**
 * @packageName: com.fufufu.service.impl
 * @className: StudentServiceImpl
 * @createTime: 2024-04-02 14:45
 * @description:
 **/

package com.fufufu.service.impl;

import com.fufufu.mapper.StudentMapper;
import com.fufufu.pojo.Account;
import com.fufufu.pojo.Course;
import com.fufufu.pojo.PageBean;
import com.fufufu.pojo.Student;
import com.fufufu.service.StudentService;
import com.fufufu.utils.Md5Util;
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
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Transactional
    @Override
    public Student findByUserName(String username) {
        Student student = studentMapper.findByUserName(username);
        return student;
    }

    @Transactional
    @Override
    public void register(Account account) {
        account.setPassword(Md5Util.getMD5String(account.getPassword()));
        account.setRole(RoleEnum.STUDENT.name());
        studentMapper.register(account);
    }


    @Transactional
    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        studentMapper.updateAvatar(avatarUrl,id);
    }

    @Transactional
    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }


    @Override
    public void updatePwd(final String newPwd) {
        final String md5String = Md5Util.getMD5String(newPwd);
        final Map<String, Object> map = ThreadLocalUtil.get();
        final Integer id = (Integer) map.get("id");
        studentMapper.updatePwd(md5String, id);
    }

    //管理员更新学生信息
    @Override
    public void updateStu(Account account) {
        account.setUpdateTime(LocalDateTime.now());
        studentMapper.updateStu(account);
    }

    //管理员添加学生用户
    @Override
    public void add(Student student) {
        student.setPassword(Md5Util.getMD5String(student.getPassword()));
        studentMapper.add(student);
    }


    //管理员删除学生用户
    @Override
    public void deleteById(Integer id) {
        studentMapper.deleteById(id);
    }

    //管理员获取学生所有数据
    @Transactional
    @Override
    public PageBean<Student> list(Integer pageNum, Integer pageSize, String username , String nickname) {
        //1.创建PageBean对象
        PageBean<Student> pb = new PageBean<>();
        //2.开启分页查询 PageHelper
        PageHelper.startPage(pageNum,pageSize);
        //3.调用mapper
        List<Student> studentList = studentMapper.selectAll(username,nickname);
        //Page中提供了方法,可以获取PageHelper分页查询后 得到的总记录条数和当前页数据
        Page<Student> p = (Page<Student>) studentList;
        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }



}
 
