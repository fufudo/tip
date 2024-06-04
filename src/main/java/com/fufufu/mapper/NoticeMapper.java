package com.fufufu.mapper;

import com.fufufu.pojo.Notice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface NoticeMapper {

    @Insert("insert into notice(title,content,create_time,create_user) values (#{title},#{content},now(),#{createUser})")
    void add(Notice notice);

    @Select("select * from notice order by create_time desc ")
    List<Notice> selectAll();

    @Update("update notice set title=#{title},content=#{content} where id=#{id} ")
    void update(Notice notice);
}
