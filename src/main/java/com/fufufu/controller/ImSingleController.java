/**
 * @packageName: com.fufufu.controller
 * @className: ImSingleController
 * @createTime: 2024-05-14 21:56
 * @description:
 **/

package com.fufufu.controller;

import cn.hutool.core.lang.Dict;
import com.fufufu.pojo.ImSingle;
import com.fufufu.pojo.Result;
import com.fufufu.service.ImSingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/imsingle")
public class ImSingleController {

//    @Autowired
//    private ImSingleService imSingleService;
//
//    /**
//     * 查询所有消息
//     */
//    @GetMapping
//    public Result findByFromUsername(@RequestParam String fromUser, @RequestParam String toUser){
//        List<ImSingle> all = imSingleService.findByUsername(fromUser , toUser);
//        return Result.success(all);
//    }
//
//    /**
//     * 查询未读消息数量
//     * @return 未读消息数量
//     */
//    @GetMapping("/unReadNums")
//    public Result findUnReadNums(@RequestParam String toUsername){
//        Dict dict = imSingleService.findUnReadNums(toUsername);
//        return Result.success(dict);
//    }

}
 
