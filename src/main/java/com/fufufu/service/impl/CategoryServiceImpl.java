package com.fufufu.service.impl;

import com.fufufu.mapper.CategoryMapper;
import com.fufufu.pojo.Category;
import com.fufufu.service.CategoryService;
import com.fufufu.utils.RoleEnum;
import com.fufufu.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Transactional
    @Override
    public void add(Category category) {
        //补充属性值
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        String role = (String) map.get("role");
        category.setCreateUser(userId);
        category.setRole(role);
        categoryMapper.add(category);
    }

    @Transactional
    @Override
    public List<Category> list() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        String role = (String) map.get("role");
        if(RoleEnum.ADMIN.name().equals(role)){
            return categoryMapper.listByAdmin();
        }else {
            return categoryMapper.list(userId , role);
        }
    }

    @Transactional
    @Override
    public Category findById(Integer id) {
        Category c = categoryMapper.findById(id);
        return c;
    }

    @Transactional
    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        categoryMapper.deleteById(id);
    }
}
