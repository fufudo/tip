package com.fufufu.mapper;

import com.fufufu.pojo.Account;
import com.fufufu.pojo.Student;
import com.fufufu.pojo.StudentCourse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

    //查询学生所有信息
    @Select("select * from student where username=#{username}")
    Student findByUserName(String username);

    //学生注册
    @Insert("insert into student(username,password,role,create_time,update_time)" +
            " values(#{username},#{password},#{role},now(),now())")
    void register(Account account);

    //学生修改个人头像
    @Update("update student set avatar=#{avatar},update_time=now() where id=#{id}")
    void updateAvatar(String avatar, Integer id);

    //学生修改个人信息
    @Update("update student set nickname=#{nickname},tel=#{tel},email=#{email},birth=#{birth}," +
            "sex=#{sex} where id=#{id}")
    void updateStu(Account account);

    //管理员获取所有学生信息
    List<Student> selectAll(String username , String nickname);

    //管理员编辑学生信息
    @Update("update student set nickname=#{nickname},tel=#{tel},email=#{email},sex=#{sex}," +
            "birth=#{birth},avatar=#{avatar}, role=#{role},update_time=#{updateTime}  where id=#{id}")
    void update(Student student);

    //管理员添加学生信息
    @Insert("insert into student(username,password,nickname,tel,email,sex,birth,avatar,role,create_time,update_time)" +
            "values (#{username},#{password},#{nickname},#{tel},#{email},#{sex},#{birth},#{avatar},#{role},now(),now())")
    void add(Student student);

    //管理员删除学生信息
    @Delete("delete from student where id=#{id}")
    void deleteById(Integer id);

    @Update("update student set password=#{md5String},update_time=now() where id=#{id}")
    void updatePwd(String md5String, Integer id);
}

























