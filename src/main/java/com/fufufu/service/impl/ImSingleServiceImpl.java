/**
 * @packageName: com.fufufu.service.impl
 * @className: ImSingleServiceImpl
 * @createTime: 2024-05-15 15:43
 * @description:
 **/

package com.fufufu.service.impl;

import cn.hutool.core.lang.Dict;
import com.fufufu.mapper.ImSingleMapper;
import com.fufufu.pojo.ImSingle;
import com.fufufu.service.ImSingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ImSingleServiceImpl implements ImSingleService {
    
//    @Autowired
//    private ImSingleMapper imSingleMapper;
//
//    @Override
//    public ImSingle add(ImSingle imSingle) {
//        imSingleMapper.insertSelective(imSingle);
//        return imSingle;
//    }
//
//    @Override
//    public List<ImSingle> findByUsername(String fromUser, String toUser) {
//        List<ImSingle> list = imSingleMapper.findByUsername(fromUser, toUser);
//        list.forEach(x -> {
//            if (x.getTouser().equals(fromUser) && x.getFromuser().equals(toUser)) {
//                x.setReaded(1);
//                imSingleMapper.updateByPrimaryKey(x);
//            }
//        });
//        return list;
//    }
//
//    @Override
//    public Dict findUnReadNums(String toUsername) {
//        List<ImSingle> list = imSingleMapper.findByToUsername(toUsername);
//        Map<String, List<ImSingle>> collect = list.stream().collect(Collectors.groupingBy(ImSingle::getFromuser));
//        Dict dict = Dict.create();
//        collect.forEach((k,v) -> dict.set(k, v.size()));
//        return dict;
//    }
}
 
