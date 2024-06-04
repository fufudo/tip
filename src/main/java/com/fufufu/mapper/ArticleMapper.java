package com.fufufu.mapper;

import com.fufufu.anno.State;
import com.fufufu.pojo.Article;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.apache.ibatis.annotations.*;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ArticleMapper {
    //新增
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,role,create_time,update_time) " +
            "values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{role},#{createTime},#{updateTime})")
    void add(Article article);




    @Update("update article set title=#{title},content=#{content},cover_img=#{coverImg},create_user=#{createUser}," +
            "category_id=#{categoryId},role=#{role},state=#{state},update_time=#{updateTime} where id=#{id}")
    void update(Article article);



    @Delete("delete from article where id=#{id}")
    void deleteById(Integer id);

    List<Article> listByAdmin(Integer userId, String role, Integer categoryId, String state);

    List<Article> list(Integer userId, String role, Integer categoryId, String state);


/*    List<Article> listByStudent(Integer userId,  String role,Integer categoryId, String state);

    List<Article> listByTeacher(Integer userId, String role, Integer categoryId, String state);*/
}

