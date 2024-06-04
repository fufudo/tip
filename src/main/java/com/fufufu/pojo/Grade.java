/**
 * @packageName: com.fufufu.pojo
 * @className: Grade
 * @createTime: 2024-04-09 22:00
 * @description:
 **/

package com.fufufu.pojo;

import lombok.Data;

@Data
public class Grade {

    private Integer id;
    private Integer courseId;
    private Integer studentId;
    private Integer teacherId;
    private Double score;
    private String comment;
    private String feedback;

    //拓展字段
    private String courseName;
    private String stuNickname;
    private String teaNickname;

}
 
