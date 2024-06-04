/**
 * @packageName: com.fufufu.pojo
 * @className: Admin
 * @createTime: 2024-04-02 14:33
 * @description:
 **/

package com.fufufu.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Admin extends Account {

    @NotNull
    private Integer id;
    @NotEmpty
    private String username;
    @Pattern(regexp = "^\\S{5,16}$")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotNull
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    @NotNull
    private String role;
    private String avatar;
}
 
