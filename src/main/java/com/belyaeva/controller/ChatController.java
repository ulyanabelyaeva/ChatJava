package com.belyaeva.controller;

import com.belyaeva.dto.ChatDto;
import com.belyaeva.service.api.ChatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class ChatController {

    private final Logger logger = Logger.getLogger(ChatController.class.getName());

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/read/chats")
    public ResponseEntity<List<ChatDto>> getChats(){
        List<ChatDto> chats = chatService.readChats();
        logger.log(Level.INFO, "Chats: {0}", chats);
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }
}
