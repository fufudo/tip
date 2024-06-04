package com.fufufu.service;

import com.fufufu.pojo.Notice;

import java.util.List;

public interface NoticeService {
    void add(Notice notice);

    List<Notice> selectAll();

    void update(Notice notice);
}
