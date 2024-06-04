/**
 * @packageName: com.fufufu.config
 * @className: WebSocketSingleServer
 * @createTime: 2024-05-15 12:32
 * @description:
 **/

package com.fufufu.config;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.fufufu.pojo.ImSingle;
import com.fufufu.service.ImSingleService;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket 单聊
 */
@ServerEndpoint(value = "/imserverSingle")
@Component
public class WebSocketSingleServer implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(WebSocketSingleServer.class);

    /**
     * 记录当前在线连接数
     */
    public static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    @Autowired
    ImSingleService imSingleService;

    static ImSingleService staticImSingleService;
    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        sessionMap.put(session.getId(), session);
        log.info("[onOpen] 新建连接，session={}, 当前在线人数为：{}", session.getId(), sessionMap.size());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        sessionMap.remove(session.getId());
        log.info("[onClose] 有一连接关闭，session={}, 当前在线人数为：{}", session.getId(), sessionMap.size());
    }

    /**
     * 收到客户端消息后调用的方法
     * 后台收到客户端发送过来的消息
     * onMessage 是一个消息的中转站
     * 接受 浏览器端 socket.send 发送过来的 json数据
     * @param //message 客户端发送过来的消息
     */
//    @OnMessage
//    public void onMessage(String message, Session fromSession) {
//        log.info("服务端收到消息:{}", message);
//        ImSingle imSingle = JSONUtil.toBean(message, ImSingle.class);
//        imSingle.setTime(LocalDateTime.now());
//        // 存储数据到数据库
//        staticImSingleService.add(imSingle);
//        String jsonStr = JSONUtil.toJsonStr(imSingle);  // 处理后的消息体
//        this.sendAllMessage(jsonStr);
//        log.info("[onMessage] 发送消息：{}", jsonStr);
//    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("[onError] 发生错误", error);
    }

    /**
     * 服务端发送消息给除了自己的其他客户端
     */
    private void sendMessage(Session fromSession, String message) {
        sessionMap.values().forEach(session -> {
            if (fromSession != session) {
                log.info("服务端给客户端[{}]发送消息{}", session.getId(), message);
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    log.error("服务端发送消息给客户端异常", e);
                }
            }
        });
    }

    /**
     * 服务端发送消息给所有客户端
     */
    private void sendAllMessage(String message) {
        try {
            for (Session session : sessionMap.values()) {
                log.info("服务端给客户端[{}]发送消息{}", session.getId(), message);
                session.getBasicRemote().sendText(message);
            }
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败", e);
        }
    }

    @Override
    public void afterPropertiesSet() {
        staticImSingleService = imSingleService;
    }
}
 
