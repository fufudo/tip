package com.fufufu.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    private static final String KEY = "itheima";

    //接收业务数据,生成token并返回
    public static String genToken(Map<String, Object> claims) {
        long timeoutHours = (Long) claims.get("timeout");
        long expirationInMillis = timeoutHours * 60 * 60 * 1000L;
        Date expiresAt = new Date(System.currentTimeMillis() + expirationInMillis);
        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(expiresAt) //24小时 168小时
                .sign(Algorithm.HMAC256(KEY));
    }

    //接收token,验证token,并返回业务数据
    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }
}
