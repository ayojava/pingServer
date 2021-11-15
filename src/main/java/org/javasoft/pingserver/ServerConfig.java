package org.javasoft.pingserver;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ServerConfig {

    private final PingSocketHandler pingSocketHandler;

    @Autowired
    public ServerConfig(PingSocketHandler pingSocketHandler) {
        this.pingSocketHandler = pingSocketHandler;
    }

    @Bean
    public HandlerMapping serverHandlerMapping(){
        Map<String, WebSocketHandler> webSocketHandlerMap = new HashMap<>();
        webSocketHandlerMap.put("/server", pingSocketHandler);

        val simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
        simpleUrlHandlerMapping.setOrder(1);
        simpleUrlHandlerMapping.setUrlMap(webSocketHandlerMap);
        return simpleUrlHandlerMapping;
    }
}
