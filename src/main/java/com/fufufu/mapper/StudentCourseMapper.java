package com.fufufu.mapper;

import com.fufufu.pojo.StudentCourse;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentCourseMapper {

    @Insert("insert into student_course (name,no,student_id,teacher_id,course_id) " +
            "values (#{name},#{no},#{studentId},#{teacherId},#{courseId})")
    void add(StudentCourse studentCourse);


    @Select("select * from student_course where student_id = #{studentId} and course_id=#{courseId}")
    StudentCourse selectByCondition(Integer courseId, Integer studentId);

    List<StudentCourse> selectAll(StudentCourse studentCourse);

    List<StudentCourse> selectAllByAdmin(StudentCourse studentCourse);

    @Delete("delete from student_course where id =#{id}")
    void deteleById(Integer id);

    @Select("select * from student_course where student_id = #{userId}")
    List<StudentCourse> isSelected(Integer userId);
}
