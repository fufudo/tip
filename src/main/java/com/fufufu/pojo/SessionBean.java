/**
 * @packageName: com.fufufu.pojo
 * @className: SessionBean
 * @createTime: 2024-04-16 15:17
 * @description:
 **/

package com.fufufu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SessionBean {
    /*
    WebSocketSession：
        这个字段用于存储与WebSocket相关的会话对象。
        WebSocketSession是Spring提供的一个接口，代表了一个WebSocket会话，
        它提供了发送和接收消息的方法，以及获取会话信息的方法。
     */
    private WebSocketSession webSocketSession;
    private Integer clientId;

}
 
