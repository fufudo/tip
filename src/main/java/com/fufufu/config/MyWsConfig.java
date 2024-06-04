/**
 * @packageName: com.fufufu.config
 * @className: MyWsConfig
 * @createTime: 2024-04-16 15:32
 * @description:
 **/

package com.fufufu.config;

import com.fufufu.interceptors.MyWsInterceptor;
import com.fufufu.ws.MyWsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket //启用WebSocket支持
//WebSocketConfigurer: 这是一个接口，它定义了如何配置WebSocket
public class MyWsConfig implements WebSocketConfigurer {

    @Autowired
    private MyWsHandler myWsHandler;

    @Autowired
    MyWsInterceptor myWsInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWsHandler,"/myWs1").addInterceptors(myWsInterceptor).setAllowedOrigins("*");
        //.setAllowedOrigins("*"): 这行代码设置了允许的来源为所有（*）。这意味着任何来源的客户端都可以尝试通过WebSocket连接到/myWs1
    }
}
 
