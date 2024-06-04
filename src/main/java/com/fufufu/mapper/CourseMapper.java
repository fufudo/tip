package com.fufufu.mapper;

import com.fufufu.pojo.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CourseMapper {


    @Insert("insert into course(name,no,description,times,teacher,role,create_user,create_time,update_time) " +
            "values (#{name},#{no},#{description},#{times},#{teacher},#{role},#{createUser},now(),now() )")
    void add(Course course);

    @Delete("delete from course where id=#{id}")
    void deleteById(Integer id);

    @Update("update course set name=#{name},no=#{no},description=#{description}," +
            "times=#{times},teacher=#{teacher},create_user=#{createUser} where id=#{id}")
    void update(Course course);

    //@Select("select * from course where name like concat('%',#{name},'%') and id=#{userId} ")
    List<Course> listByTeacher(Integer userId, String role, String name , String no, String teacher);

    List<Course> listByAdmin(Integer userId, String role, String name , String no, String teacher);

    List<Course> listByStudent(Integer userId, String role, String name , String no, String teacher);
}
