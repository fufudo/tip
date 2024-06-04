/**
 * @packageName: com.fufufu.controller
 * @className: TeacherController
 * @createTime: 2024-04-07 00:53
 * @description:
 **/

package com.fufufu.controller;

import com.fufufu.pojo.*;
import com.fufufu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
@Validated
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    
    @PostMapping
    public Result add(@RequestBody @Validated  Teacher teacher) {
        Teacher teacher1 = teacherService.findByUserName(teacher.getUsername());
        if(teacher1 != null){
            return Result.error("账号已注册");
        }
        teacherService.add(teacher);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(Integer id){
        teacherService.deleteById(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody @Validated Account account){
        teacherService.update(account);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Teacher>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String nickname
    ){
        PageBean<Teacher> pb = teacherService.list(pageNum, pageSize ,username,nickname);
        return Result.success(pb);
    }
}
 
