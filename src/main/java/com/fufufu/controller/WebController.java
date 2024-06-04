/**
 * @packageName: com.fufufu.controller
 * @className: WebController
 * @createTime: 2024-04-02 15:19
 * @description:
 **/
package com.fufufu.controller;

import com.fufufu.pojo.*;
import com.fufufu.service.AdminService;
import com.fufufu.service.StudentService;
import com.fufufu.service.TeacherService;
import com.fufufu.utils.AuthUtil;
import com.fufufu.utils.Md5Util;
import com.fufufu.utils.RoleEnum;
import com.fufufu.utils.ThreadLocalUtil;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.fufufu.interceptors.LoginInterceptor.AUTHORIZATION_HEADER;


@RestController
@RequestMapping("/account")
@Validated
public class WebController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    /*
            if (RoleEnum.ADMIN.name().equals(account.getRole())) {

            } else if (RoleEnum.STUDENT.name().equals(account.getRole())) {

            }
    */
    @PostMapping("/login")
    public Result<String> login(@RequestBody Account account) {
        //System.out.println("account.getIsChecked():" +account.getIsChecked());
        Account loginUser;
        String token = null;
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            loginUser = adminService.findByUserName(account.getUsername());
            token = AuthUtil.authenticateAndGenerateToken(loginUser, account, stringRedisTemplate);
        } else if (RoleEnum.STUDENT.name().equals(account.getRole())) {
            loginUser = studentService.findByUserName(account.getUsername());
            token = AuthUtil.authenticateAndGenerateToken(loginUser, account, stringRedisTemplate);
        } else if (RoleEnum.TEACHER.name().equals(account.getRole())) {
            loginUser = teacherService.findByUserName(account.getUsername());
            token = AuthUtil.authenticateAndGenerateToken(loginUser, account, stringRedisTemplate);
        }

        if (token != null) {
            return Result.success(token); // 登录成功，返回令牌
        } else {
            return Result.error("用户名错误"); // 用户名错误或角色登录错误（可进一步细分错误类型）
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody Account account) {
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            Account a = adminService.findByUserName(account.getUsername());
            if (a == null) {
                adminService.register(account);
            } else {
                return Result.error("账号已注册");
            }
        } else if (RoleEnum.STUDENT.name().equals(account.getRole())) {
            Account b = studentService.findByUserName(account.getUsername());
            if (b == null) {
                studentService.register(account);
            } else {
                return Result.error("账号已注册");
            }
        } else if (RoleEnum.TEACHER.name().equals(account.getRole())) {
            Account b = teacherService.findByUserName(account.getUsername());
            if (b == null) {
                teacherService.register(account);
            } else {
                return Result.error("账号已注册");
            }
        }
        return Result.success();
    }

    //    通过AuthUtil绑定role到Map<String, Object> claims
    @GetMapping("/info")
    public Result<Account> accountInfo() {
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        String role = (String) map.get("role");

        if (RoleEnum.ADMIN.name().equals(role)) {
            Account account = adminService.findByUserName(username);
            return Result.success(account);
        } else if (RoleEnum.STUDENT.name().equals(role)) {
            Account account = studentService.findByUserName(username);
            return Result.success(account);
        } else if (RoleEnum.TEACHER.name().equals(role)) {
            Account account = teacherService.findByUserName(username);
            return Result.success(account);
        }

        return Result.error("获取用户信息错误！");
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated Account account) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        String role = (String) map.get("role");
        if (account.getId().equals(id) && role.equals(RoleEnum.ADMIN.name())) {
            adminService.update(account);
            return Result.success();
        } else if (account.getId().equals(id) && role.equals(RoleEnum.STUDENT.name())) {
            studentService.updateStu(account);
            return Result.success();
        } else if (account.getId().equals(id) && role.equals(RoleEnum.TEACHER.name())) {
            teacherService.update(account);
            return Result.success();
        } else {
            return Result.error("不是本人");
        }

    }

    @PatchMapping("updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        Map<String, Object> map = ThreadLocalUtil.get();
        String role = (String) map.get("role");

        if (role.equals(RoleEnum.ADMIN.name())) {
            adminService.updateAvatar(avatarUrl);
            return Result.success();
        } else if (role.equals(RoleEnum.STUDENT.name())) {
            studentService.updateAvatar(avatarUrl);
            return Result.success();
        } else if (role.equals(RoleEnum.TEACHER.name())) {
            teacherService.updateAvatar(avatarUrl);
            return Result.success();
        }

        return Result.error("更新头像出现错误");
    }

    @PatchMapping("/updatePwd")
    public Result<String> updatePwd(@RequestBody Map<String, String> params, @RequestHeader(AUTHORIZATION_HEADER) final String token) {
        final String oldPwd = params.get("old_pwd");
        final String newPwd = params.get("new_pwd");
        final String rePwd = params.get("re_pwd");
        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少必要的参数");
        }

        final Map<String, Object> map = ThreadLocalUtil.get();
        final String username = (String) map.get("username");
        final String role = (String) map.get("role");
        if (RoleEnum.ADMIN.name().equals(role)) {
            final Account account = adminService.findByUserName(username);
            if (!rePwd.equals(newPwd)) {
                return Result.error("两次结果不一样");
            }
            if (!account.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
                return Result.error("密码填写不正确");
            }
            adminService.updatePwd(newPwd);
        } else if (RoleEnum.STUDENT.name().equals(role)) {
            final Account account = studentService.findByUserName(username);
            if (!rePwd.equals(newPwd)) {
                return Result.error("两次结果不一样");
            }
            if (!account.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
                return Result.error("密码填写不正确");
            }
            studentService.updatePwd(newPwd);
        } else if (RoleEnum.TEACHER.name().equals(role)) {
            final Account account = teacherService.findByUserName(username);
            if (!rePwd.equals(newPwd)) {
                return Result.error("两次结果不一样");
            }
            if (!account.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
                return Result.error("密码填写不正确");
            }
            teacherService.updatePwd(newPwd);
        }

        // Delete token in redis.
        final ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);

        return Result.success();
    }

}

