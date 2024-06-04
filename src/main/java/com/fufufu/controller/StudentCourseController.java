/**
 * @packageName: com.fufufu.controller
 * @className: StudentCourseController
 * @createTime: 2024-04-07 14:30
 * @description:
 **/

package com.fufufu.controller;

import com.fufufu.pojo.PageBean;
import com.fufufu.pojo.Result;
import com.fufufu.pojo.StudentCourse;
import com.fufufu.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studentCourse")
public class StudentCourseController {

    @Autowired
    private StudentCourseService studentCourseService;

    @PostMapping
    public Result add(@RequestBody  StudentCourse studentCourse){
        //System.out.println("dawdawdawdawdawdawd===   "+studentCourse.getTeacherId());
        //判断是否已经选过此课程
        StudentCourse course = studentCourseService.selectByCondition(studentCourse.getCourseId(),studentCourse.getStudentId());
        if(course == null){
            studentCourseService.add(studentCourse);
            return Result.success();
        }else {
            return Result.error("您已选过此课程");
        }
    }

    @GetMapping
    public Result<PageBean<StudentCourse>> list(
            Integer pageNum,
            Integer pageSize,
            StudentCourse studentCourse
    ){
        PageBean<StudentCourse> pb = studentCourseService.list(pageNum, pageSize ,studentCourse);
//        System.out.println("----------------------");
//        System.out.println(pb.getItems());
//        System.out.println("----------------------");
        return Result.success(pb);
    }

    @DeleteMapping
    public Result deteleById(Integer id){
        studentCourseService.deteleById(id);
        return Result.success();
    }

    @GetMapping("/selectById")
    public Result<List<StudentCourse>> isSelected(){
        //传入的id是course的id
        List<StudentCourse> list =  studentCourseService.isSelected();
        return Result.success(list);
    }

}
 
