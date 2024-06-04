package com.fufufu.interceptors;

import com.fufufu.exception.TokenExpiredException;
import com.fufufu.utils.JwtUtil;
import com.fufufu.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) throws Exception {
        //令牌验证
        final String token = request.getHeader(AUTHORIZATION_HEADER);
        //验证token
        try {
            //从redis中获取相同的token
            final ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            final String redisToken = operations.get(token);
            if (redisToken==null){
                //token已经失效了
                throw new TokenExpiredException("Token已过期，请重新登录");
            }
            final Map<String, Object> claims = JwtUtil.parseToken(token);

            //把业务数据存储到ThreadLocal中
            ThreadLocalUtil.set(claims);
            //放行
            return true;
        } catch (Exception e) {
            //http响应状态码为401
            response.setStatus(401);
            //不放行
            return false;
        }
    }

    @Override
    public void afterCompletion(final HttpServletRequest request,
                                final HttpServletResponse response,
                                final Object handler,
                                Exception ex) throws Exception {
        //清空ThreadLocal中的数据
        ThreadLocalUtil.remove();
    }
}
