/**
 * @packageName: com.fufufu.controller
 * @className: StudentController
 * @createTime: 2024-04-03 14:56
 * @description:
 **/

package com.fufufu.controller;
import com.fufufu.pojo.Course;
import com.fufufu.pojo.PageBean;
import com.fufufu.pojo.Result;
import com.fufufu.pojo.Student;
import com.fufufu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@Validated
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public Result add(@RequestBody @Validated Student student) {
        Student student1 = studentService.findByUserName(student.getUsername());
        if(student1 != null){
            return Result.error("账号已注册");
        }
        studentService.add(student);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(Integer id){
        studentService.deleteById(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody @Validated Student student){
        studentService.update(student);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Student>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String nickname
    ){
        PageBean<Student> pb = studentService.list(pageNum, pageSize ,username,nickname);
        return Result.success(pb);
    }

}
 
