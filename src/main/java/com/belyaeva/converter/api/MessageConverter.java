package com.belyaeva.converter.api;

import com.belyaeva.dto.MessageDto;
import com.belyaeva.model.Chat;
import com.belyaeva.model.Message;
import com.belyaeva.model.User;

import java.util.List;

public interface MessageConverter {

    Message convertFromDto(MessageDto messageDto, User sender, Chat chat);

    MessageDto convertToDto(Message message);

    List<MessageDto> convertToDtoList(List<Message> messages);
}
