package com.fufufu.utils;

import com.fufufu.pojo.Account;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AuthUtil {

    private AuthUtil() {
        // 私有构造方法，防止实例化  
    }

    public static String authenticateAndGenerateToken(
            Account loginUser,
            Account account,
            StringRedisTemplate stringRedisTemplate
            ) {
        if (loginUser != null && Md5Util.getMD5String(account.getPassword()).equals(loginUser.getPassword())) {
            // 登录成功，生成JWT令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            claims.put("role", loginUser.getRole());
            claims.put("timeout",account.getIsChecked());
            String token = JwtUtil.genToken(claims);
            // 把token存储到redis中, 使用account.getIsChecked()的值作为过期时间
            long expirationTime = account.getIsChecked().longValue();
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token, token, expirationTime, TimeUnit.HOURS);

            return token;
        }
        return null; // 认证失败返回null  
    }
}