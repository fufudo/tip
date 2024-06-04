/**
 * @packageName: com.fufufu.service.impl
 * @className: NoticeServiceImpl
 * @createTime: 2024-04-18 23:54
 * @description:
 **/

package com.fufufu.service.impl;

import com.fufufu.mapper.NoticeMapper;
import com.fufufu.pojo.Notice;
import com.fufufu.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public void add(Notice notice) {
        noticeMapper.add(notice);
    }

    @Override
    public List<Notice> selectAll() {
        return noticeMapper.selectAll();
    }

    @Override
    public void update(Notice notice) {
        noticeMapper.update(notice);
    }
}
 
