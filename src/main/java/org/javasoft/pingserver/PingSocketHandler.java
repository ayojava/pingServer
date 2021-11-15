package org.javasoft.pingserver;

import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
public class PingSocketHandler implements WebSocketHandler {

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {
        final val messageFlux = webSocketSession.receive()
                .map(WebSocketMessage::getPayloadAsText)
                .map(mapResponse)
                .map(webSocketSession::textMessage);
        return webSocketSession.send(messageFlux);
    }

    private Function<String, String> mapResponse = input -> StringUtils.equalsIgnoreCase(input, "PING") ? "PONG" : "Another Message";

}
