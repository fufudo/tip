package com.fufufu.service;

import com.fufufu.pojo.Article;
import com.fufufu.pojo.PageBean;

public interface ArticleService {
    //新增文章
    void add(Article article);

    //条件分页列表查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    void update(Article article);

    void deleteById(Integer id);
}
