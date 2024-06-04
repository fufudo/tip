/**
 * @packageName: com.fufufu.controller
 * @className: CourseController
 * @createTime: 2024-03-31 16:02
 * @description:
 **/

package com.fufufu.controller;


import com.fufufu.pojo.Course;
import com.fufufu.pojo.PageBean;
import com.fufufu.pojo.Result;
import com.fufufu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
@Validated
public class CourseController {

    @Autowired
    private CourseService courseService;

    @DeleteMapping
    public Result delete(Integer id){
        courseService.deleteById(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody @Validated(Course.Update.class) Course course){
        courseService.update(course);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody @Validated(Course.Add.class) Course course) {
        courseService.add(course);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Course>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) String name ,
            @RequestParam(required = false) String no ,
            @RequestParam(required = false) String teacher
    ){
        PageBean<Course> pb = courseService.list(pageNum, pageSize ,name,no,teacher);
        System.out.println("-------------------------"+pb.getItems()+"-------------------------");
        return Result.success(pb);
    }

/*    @GetMapping("/byAdmin")
    public Result<PageBean<Course>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) String name
    ){
        PageBean<Course> pb = courseService.list(pageNum, pageSize ,name);
        return Result.success(pb);
    }*/

}
 
