/**
 * @packageName: com.fufufu.service.impl
 * @className: TeacherServiceImpl
 * @createTime: 2024-04-05 14:14
 * @description:
 **/

package com.fufufu.service.impl;

import com.fufufu.mapper.TeacherMapper;
import com.fufufu.pojo.*;
import com.fufufu.service.TeacherService;
import com.fufufu.utils.Md5Util;
import com.fufufu.utils.RoleEnum;
import com.fufufu.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Teacher findByUserName(String username) {
        Teacher teacher = teacherMapper.findByUserName(username);
        return teacher;
    }

    @Override
    public void register(Account account) {
        account.setPassword(Md5Util.getMD5String(account.getPassword()));
        account.setRole(RoleEnum.TEACHER.name());
        teacherMapper.register(account);
    }

    //测试一下，管理员和教师都调用此方法进行资料修改
    @Override
    public void update(Account account) {
        teacherMapper.update(account);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        teacherMapper.updateAvatar(avatarUrl,id);
    }

    //管理员添加教师用户
    @Override
    public void add(Teacher teacher) {
        teacher.setPassword(Md5Util.getMD5String(teacher.getPassword()));
        teacherMapper.add(teacher);
    }

    //管理员根据id删除教师用户
    @Override
    public void deleteById(Integer id) {
        teacherMapper.deleteById(id);
    }

    //管理员获取教师页面数据
    @Override
    public PageBean<Teacher> list(Integer pageNum, Integer pageSize, String username, String nickname) {
        //1.创建PageBean对象
        PageBean<Teacher> pb = new PageBean<>();
        //2.开启分页查询 PageHelper
        PageHelper.startPage(pageNum,pageSize);
        //3.调用mapper
        List<Teacher> list = teacherMapper.selectAll(username,nickname);
        //Page中提供了方法,可以获取PageHelper分页查询后 得到的总记录条数和当前页数据
        Page<Teacher> p = (Page<Teacher>) list;
        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public void updatePwd(final String newPwd) {
        final String md5String = Md5Util.getMD5String(newPwd);
        final Map<String, Object> map = ThreadLocalUtil.get();
        final Integer id = (Integer) map.get("id");
        teacherMapper.updatePwd(md5String, id);
    }
}
 
