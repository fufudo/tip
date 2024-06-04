/**
 * @packageName: com.fufufu.interceptors
 * @className: MyWsInterceptor
 * @createTime: 2024-04-16 15:11
 * @description:
 **/

package com.fufufu.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

/**
 * 握手拦截器
 * beforeHandshake 方法在WebSocket握手之前被调用。在这个方法中，你可以添加自定义的逻辑，比如验证、记录日志等。
 * afterHandshake 方法在WebSocket握手完成后被调用。这个方法可以用来执行握手完成后的逻辑，比如统计、进一步验证等。
 */

/**
 * 握手之前：
     * 在WebSocket握手之前，客户端和服务器之间通常使用HTTP协议进行通信。客户端想要建立一个WebSocket连接时，
     * 它会首先发送一个特殊的HTTP请求到服务器。这个请求通常称为“握手请求”，它包含一些特定的头信息，比如Upgrade: websocket，
     * 表示客户端希望将连接升级到WebSocket协议。
 * 握手过程：
     * 客户端发送WebSocket握手请求，包含Upgrade和Connection等特定的HTTP头。
     * 服务器接收到客户端的握手请求后，会进行验证和处理。如果服务器支持WebSocket协议，并且验证通过，
     * 它将返回一个HTTP响应，称为“握手响应”。这个响应中包含了一个状态码为101的HTTP响应，表示协议已经成功升级到了WebSocket。
     * 客户端验证握手响应，如果验证成功，则WebSocket连接建立成功。
 * 握手之后：
     * 一旦WebSocket握手成功，客户端和服务器之间就可以通过WebSocket协议进行双向数据传输了。
     * 这意味着服务器可以主动向客户端推送信息，客户端也可以主动向服务器发送信息，实现了真正的双向平等对话。
     * 此时，数据传输不再使用HTTP协议，而是使用WebSocket协议。在WebSocket连接保持打开的状态下，双方可以随时发送和接收消息，
     * 直到连接被关闭。
 */
@Slf4j
@Component
public class MyWsInterceptor extends HttpSessionHandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        log.info(request.getRemoteAddress().toString() + "开始握手");
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        log.info(request.getRemoteAddress().toString() + "完成握手");
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
 
