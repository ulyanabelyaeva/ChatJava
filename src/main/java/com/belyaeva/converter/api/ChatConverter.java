package com.belyaeva.converter.api;

import com.belyaeva.dto.ChatDto;
import com.belyaeva.model.Chat;
import com.belyaeva.model.User;

import java.util.List;

public interface ChatConverter {

    ChatDto convertToDto(Chat chat, User currentUser);

    List<ChatDto> convertToDtoList(List<Chat> chats, User currentUser);
}
