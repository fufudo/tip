package com.fufufu.mapper;

import com.fufufu.pojo.Account;
import com.fufufu.pojo.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeacherMapper {
    @Select("select * from teacher where username=#{username}")
    Teacher findByUserName(String username);

    @Insert("insert into teacher(username,password,role,create_time,update_time)" +
            " values(#{username},#{password},#{role},now(),now())")
    void register(Account account);

    //教师管理员共用同一个方法修改资料
    @Update("update teacher set nickname=#{nickname},tel=#{tel},email=#{email},sex=#{sex}," +
            "birth=#{birth},avatar=#{avatar}, role=#{role},update_time=now() where id=#{id}")
    void update(Account account);

    @Update("update teacher set avatar=#{avatar},update_time=now() where id=#{id}")
    void updateAvatar(String avatar, Integer id);

    @Insert("insert into teacher(username,password,nickname,tel,email,sex,birth,avatar,role,create_time,update_time)" +
            "values (#{username},#{password},#{nickname},#{tel},#{email},#{sex},#{birth},#{avatar},#{role},now(),now())")
    void add(Teacher teacher);

    @Delete("delete from teacher where id=#{id}")
    void deleteById(Integer id);

    List<Teacher> selectAll(String username, String nickname);

    @Update("update teacher set password=#{md5String},update_time=now() where id=#{id}")
    void updatePwd(String md5String, Integer id);
}
