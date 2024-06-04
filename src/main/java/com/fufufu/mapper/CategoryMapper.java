package com.fufufu.mapper;

import com.fufufu.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    //新增
    @Insert("insert into category(category_name,category_alias,role,create_user,create_time,update_time) " +
            "values(#{categoryName},#{categoryAlias},#{role},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);

    //根据id查询
    @Select("select * from category where id = #{id}")
    Category findById(Integer id);

    //更新
    @Update("update category set category_name=#{categoryName},category_alias=#{categoryAlias},update_time=#{updateTime} where id=#{id}")
    void update(Category category);

    //根据id删除
    @Delete("delete from category where id=#{id}")
    void deleteById(Integer id);

    //查询所有
    @Select("select * from category where create_user = #{userId} and role=#{role} ")
    List<Category> list(Integer userId , String role);


    List<Category> listByAdmin();
}
