package com.fufufu.mapper;

import com.fufufu.pojo.ImSingle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImSingleMapper {

    @Select("select * from imsingle where (fromuser = #{fromUser} and touser = #{toUser}) or (fromuser = #{toUser} and touser = #{fromUser})")
    List<ImSingle> findByUsername(String fromUser, String toUser);

    @Select("select * from imsingle where touser = #{toUser} and readed = 0")
    List<ImSingle> findByToUsername(String toUser);


}
