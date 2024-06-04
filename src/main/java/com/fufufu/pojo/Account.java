/**
 * @packageName: com.fufufu.pojo
 * @className: Account
 * @createTime: 2024-04-02 15:13
 * @description:
 **/

package com.fufufu.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Integer id;
    @NotNull
    @Pattern(regexp = "^\\S{5,16}$")
    private String username;

    @Pattern(regexp = "^\\S{5,16}$")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password; // 这个字段在序列化(java对象->js对象)时会被忽略，但反序列化(js对象->java对象)时会设置值

    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;

    //@Pattern(regexp = "^\\S{11}$") 这里不指名传入值可为空时，不传值会导致参数校验失败
    @Pattern(regexp = "^(|\\S{11})$")
    private String tel;

    @Email(message = "Invalid email format")
    private String email;

    private String sex;
    private String birth;
    private String avatar;
    @NotNull
    private String role;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    //默认为24小时，被选中值为168小时
    private Long isChecked;
}
 
