package com.fufufu.service;

import com.fufufu.pojo.Account;
import com.fufufu.pojo.Course;
import com.fufufu.pojo.PageBean;
import com.fufufu.pojo.Student;

public interface StudentService {


    Student findByUserName(String username);

    void register(Account account);


    void deleteById(Integer id);

    void update(Student student);

    PageBean<Student> list(Integer pageNum, Integer pageSize, String username , String nickname);

    void add(Student student);

    void updateAvatar(String avatarUrl);

    void updateStu(Account account);

    void updatePwd(final String newPwd);
}
