/**
 * @packageName: com.fufufu.controller
 * @className: NoticeController
 * @createTime: 2024-04-18 23:53
 * @description:
 **/

package com.fufufu.controller;

import com.fufufu.pojo.Article;
import com.fufufu.pojo.Notice;
import com.fufufu.pojo.PageBean;
import com.fufufu.pojo.Result;
import com.fufufu.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @PostMapping
    public Result add(@RequestBody Notice notice){
        noticeService.add(notice);
        return Result.success();
    }

    @GetMapping
    public Result<List<Notice>> selectAll(){
        List<Notice> notices = noticeService.selectAll();
        return Result.success(notices);
    }

    @PutMapping
    public Result update(@RequestBody Notice notice){
        noticeService.update(notice);
        return Result.success();
    }

}
 
