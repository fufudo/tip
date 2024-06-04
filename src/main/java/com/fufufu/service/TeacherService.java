package com.fufufu.service;

import com.fufufu.pojo.Account;
import com.fufufu.pojo.PageBean;
import com.fufufu.pojo.Teacher;

public interface TeacherService {
    Teacher findByUserName(String username);

    void register(Account account);

    void update(Account account);

    void updateAvatar(String avatarUrl);

    void add(Teacher teacher);

    void deleteById(Integer id);

    PageBean<Teacher> list(Integer pageNum, Integer pageSize, String username, String nickname);

    void updatePwd(final String newPwd);
}
