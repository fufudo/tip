package com.fufufu.mapper;

import com.fufufu.pojo.Account;
import com.fufufu.pojo.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminMapper {

    @Select("select * from admin where username=#{username}")
    Admin findByUserName(String username);

    @Insert("insert into admin(username,password,role,create_time,update_time)" +
            " values(#{username},#{password},#{role},now(),now())")
    void add(Account account);

    @Update("update admin set nickname=#{nickname},update_time=now() where id=#{id}")
    void update(Account account);

    @Update("update admin set avatar=#{avatar},update_time=now() where id=#{id}")
    void updateAvatar(String avatar, Integer id);

    @Update("update admin set password=#{md5String},update_time=now() where id=#{id}")
    void updatePwd(String md5String, Integer id);
}
