package com.fufufu.pojo;
/**
 * ^ 表示字符串的开始
 * \\s* 允许字符串开始有任意数量的空白字符（包括空格、制表符等）
 * \\S 要求至少有一个非空白字符
 * .{0,28} 允许之后有最多28个任意字符（这样总长度不超过30，考虑到已经有一个非空白字符）
 * \\S 要求字符串的倒数第二个字符也是非空白字符，以确保没有尾随空格
 * \\s* 允许字符串末尾有任意数量的空白字符
 * $ 表示字符串的结束
 */

import com.fufufu.anno.State;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
@Data
public class Article {
    @NotNull(groups = Update.class)
    private Integer id;//主键ID

    @NotEmpty
    @Pattern(regexp = "^.{1,30}$")
    private String title;//文章标题
    @NotEmpty
    private String content;//文章内容
    //@NotEmpty
    @URL
    private String coverImg;//封面图像

    @State
    private String state;//发布状态 已发布|草稿

    private String role;

    @NotNull
    private Integer categoryId;//文章分类id
    private Integer createUser;//创建人ID
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间

    private String stuNickname;
    private String teaNickname;

    /**
     *  private String stu_nickname 千万别这么书写！！！无论逻辑是否正确都会报错！！！
     *  private String stu_nickname 千万别这么书写！！！无论逻辑是否正确都会报错！！！
     *  private String stu_nickname 千万别这么书写！！！无论逻辑是否正确都会报错！！！
     */

    public interface Add extends Default {

    }

    public interface Update extends Default{

    }
}
