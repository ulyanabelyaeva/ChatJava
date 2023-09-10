package com.belyaeva.service.api;

import com.belyaeva.dto.ChatDto;
import com.belyaeva.dto.MessageDto;

import java.util.List;

public interface ChatService {

    List<ChatDto> readChats();

    void saveMessage(MessageDto messageDto);
}
