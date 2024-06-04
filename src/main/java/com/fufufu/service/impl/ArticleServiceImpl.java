package com.fufufu.service.impl;

import com.fufufu.utils.RoleEnum;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.fufufu.mapper.ArticleMapper;
import com.fufufu.pojo.Article;
import com.fufufu.pojo.PageBean;
import com.fufufu.service.ArticleService;
import com.fufufu.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Transactional
    @Override
    public void add(Article article) {
        //补充属性值
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        String role = (String) map.get("role");
        article.setCreateUser(userId);
        article.setRole(role);
        articleMapper.add(article);
    }

    @Transactional
    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //1.创建PageBean对象
        PageBean<Article> pb = new PageBean<>();

        //2.开启分页查询 PageHelper
        PageHelper.startPage(pageNum,pageSize);

        //3.调用mapper
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        String role = (String) map.get("role");
        List<Article> as = null;
        if(RoleEnum.STUDENT.name().equals(role) || RoleEnum.TEACHER.name().equals(role)  ){
            as = articleMapper.list(userId,role,categoryId,state);
        }/*else if (RoleEnum.TEACHER.name().equals(role)) {
            as = articleMapper.listByTeacher(userId,role,categoryId,state);
        }*/ else if (RoleEnum.ADMIN.name().equals(role)) {
            as = articleMapper.listByAdmin(userId,role,categoryId,state);
        }

        //Page中提供了方法,可以获取PageHelper分页查询后 得到的总记录条数和当前页数据
        Page<Article> p = (Page<Article>) as;

        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Transactional
    @Override
    public void update(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.update(article);
    }

    @Override
    public void deleteById(Integer id) {
        articleMapper.deleteById(id);
    }
}
