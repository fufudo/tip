/**
 * @packageName: com.fufufu.pojo
 * @className: StudentCourse
 * @createTime: 2024-04-07 14:28
 * @description:
 **/

package com.fufufu.pojo;

import lombok.Data;

@Data
public class StudentCourse {

    private Integer id;
    private String name;
    private String no;
    private Integer studentId;
    private Integer teacherId;
    private Integer courseId;

    //添加这个属性来接收student.nickname的值
    //而且在StudentCourseMapper.xml中使用了多表查询左连接方式
    //思考？数据库并没有nickname这个字段，在这里添加nickname属性会违背什么原则？效率问题？

    private String stuNickname;

    private String stuUsername;

    private String teaNickname;
}
 
