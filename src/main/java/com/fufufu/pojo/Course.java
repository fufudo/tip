/**
 * @packageName: com.fufufu.pojo
 * @className: course
 * @createTime: 2024-03-31 16:00
 * @description: 课程信息
 **/

package com.fufufu.pojo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @NotNull(groups = Course.Update.class)
    private Integer id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String no;
    @NotEmpty
    private String description;

    private String times;

    private String teacher;

    private Integer createUser;

    private String role;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    //扩展属性
    private String teaNickname;
    private String adminNickname;

    public interface Add extends Default {

    }

    public interface Update extends Default{

    }
}
 
