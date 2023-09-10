package com.belyaeva.controller;

import com.belyaeva.dto.MessageDto;
import com.belyaeva.service.api.ChatService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class SocketMessageController {

    private final Logger logger = Logger.getLogger(SocketMessageController.class.getName());

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatService chatService;

    public SocketMessageController(SimpMessagingTemplate simpMessagingTemplate,
                                   ChatService chatService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.chatService = chatService;
    }

    /**
     * Методы анотированные MessageMapping нужны для обработки сообщений от клиента
     * */
    @MessageMapping("/private-message")
    public MessageDto sendMessage(@Payload MessageDto message) {
        simpMessagingTemplate.convertAndSendToUser(message.getChatId(),"/private", message);
        logger.log(Level.INFO, "Message received: {0}", message);
        chatService.saveMessage(message);
        return message;
    }
}
