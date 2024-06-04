/**
 * @packageName: com.fufufu.controller
 * @className: gradeController
 * @createTime: 2024-04-09 21:58
 * @description:
 **/

package com.fufufu.controller;

import com.fufufu.pojo.Grade;
import com.fufufu.pojo.PageBean;
import com.fufufu.pojo.Result;
import com.fufufu.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @PostMapping
    public Result add(@RequestBody Grade grade) {
        if(gradeService.selectByCondition(grade) == null){
            gradeService.insert(grade);
            return Result.success();
        }else {
            return Result.error("课程评价已上传");
        }
    }

    @DeleteMapping
    public Result deleteById(Integer id) {
        gradeService.deleteById(id);
        return Result.success();
    }

    @PutMapping("/add")
    public Result update(@RequestBody Grade grade) {
        gradeService.update(grade);
        return Result.success();
    }

    @PostMapping("/upload")
    public Result uploadCourse(@RequestBody Grade grade) {
        //判断课程是否已经上传
        System.out.println("获取到的老师ID："+grade.getTeacherId());
        if(gradeService.selectByCondition(grade) == null){
            gradeService.uploadCourse(grade);
            return Result.success();
        }else{
            return Result.error("已存在");
        }
    }

    @GetMapping
    public Result<PageBean<Grade>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10")Integer pageSize,
            Grade grade
    ) {
        PageBean<Grade> pb = gradeService.list(pageNum, pageSize, grade);
        return Result.success(pb);
    }


}
 
