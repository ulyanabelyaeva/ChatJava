package com.belyaeva.controller;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.util.logging.Logger;

/**
 * КОнпонент для отслеживания соединения и отсоединения с Websocket сервером
 * */
@Component
public class WebSocketEventListener {

    private final Logger LOG = Logger.getLogger(WebSocketEventListener.class.getName());

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        LOG.info("Received a new web socket connection");
    }
}
