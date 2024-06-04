/**
 * @packageName: com.fufufu.pojo
 * @className: Notice
 * @createTime: 2024-04-18 23:48
 * @description:
 **/

package com.fufufu.pojo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {

    private Integer id;
    @NotEmpty
    @Pattern(regexp = "^.{2,30}$", message = "标题长度必须在2到30个字符之间")
    private String title;
    @NotEmpty
    @Pattern(regexp = "^.{5,1000}$", message = "内容长度必须在5到1000个字符之间，可以包含空格")
    private String content;
    private LocalDateTime createTime;
    private String createUser;

}
 
