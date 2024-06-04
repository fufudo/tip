package com.fufufu.mapper;

import com.fufufu.pojo.Grade;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GradeMapper {

    @Insert("insert into grade(course_id,student_id,teacher_id,score,comment,feedback) " +
            "values (#{courseId},#{studentId},#{teacherId},#{score},#{comment},#{feedback})")
    void insert(Grade grade);


    @Update("update grade set score=#{score},comment=#{comment},feedback=#{feedback} where id=#{id}")
    void update(Grade grade);

    @Delete("delete from grade where id = #{id} ")
    void deleteById(Integer id);

    @Insert("insert into grade(course_id,student_id,teacher_id) values (#{courseId},#{studentId},#{teacherId})")
    void uploadCourse(Grade grade);

    @Select("select * from grade where course_id=#{courseId} and student_id=#{studentId}")
    Grade selectByCondition(Integer courseId, Integer studentId);


    List<Grade> selectByStudent(Grade grade);

    List<Grade> selectAll(Grade grade);
    List<Grade> selectByTeacher(Grade grade);
}
