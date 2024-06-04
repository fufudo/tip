/**
 * @packageName: com.fufufu.pojo
 * @className: ImSingle
 * @createTime: 2024-05-14 22:01
 * @description:
 **/

package com.fufufu.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImSingle {

    private Integer id;
    private String content;
    private String fromuser;
    private String fromavatar;
    private LocalDateTime time ;
    private String type;
    private String touser;
    private String toavatar;
    private Integer readed;
}
 
