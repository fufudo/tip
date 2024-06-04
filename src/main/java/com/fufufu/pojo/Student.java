/**
 * @packageName: com.fufufu.pojo
 * @className: Student
 * @createTime: 2024-04-02 14:14
 * @description:
 **/

package com.fufufu.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Student extends Account{
/*    @NotNull
    private Integer id;

    @Pattern(regexp = "^\\S{5,16}$")
    private String username;

    @Pattern(regexp = "^\\S{5,16}$")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;

    @Pattern(regexp = "^\\S{11}$")
    private String tel;

    @Email(message = "Invalid email format")
    private String email;

    private String sex;
    private String birth;
    private String avatar;

    @NotNull
    private String role;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;*/
}
 
