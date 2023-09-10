package com.belyaeva.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Для личного чата
 * Клиент подписывается на '/user/'+ interlocutor.id + '/private'
 * Клиент отправляет сообщения на "/app/private-message"
 * Сервер имеет контроллер с @MessageMapping("/private-message"), который принимает личное сообщение
 * Сообщение кладется в брокер на адрес '/user/'+ interlocutor.id + '/private'
 * */
@Configuration
@EnableWebSocketMessageBroker //включает Websocket сервер
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * По этой конечной точке клиенты будут подключаться к Websocket серверу
     * */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    /**
     * Настраиваем брокер сообщений, который будет использоваться для направления сообщений от одного клиента к другому.
     *
     * Сообщения, чей адрес начинается с  "/app", должны быть направлены в методы, занимающиеся обработкой сообщений.
     * Сообщения, чей адрес начинается с  "/topic", должны быть направлены в брокер сообщений. Брокер перенаправляет
     * сообщения всем клиентам, подписанным на тему - если групповой чат
     * Сообщения, чей адрес начинается на "/user", должны быть направлены конкретному пользователю
     * */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/user");
        registry.setUserDestinationPrefix("/user");
    }
}
