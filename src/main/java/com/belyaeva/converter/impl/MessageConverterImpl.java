package com.belyaeva.converter.impl;

import com.belyaeva.converter.api.MessageConverter;
import com.belyaeva.dto.MessageDto;
import com.belyaeva.model.Chat;
import com.belyaeva.model.Message;
import com.belyaeva.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageConverterImpl implements MessageConverter {

    @Override
    public Message convertFromDto(MessageDto messageDto, User sender, Chat chat) {
        return new Message()
                .setMessage(messageDto.getMessage())
                .setSender(sender)
                .setChat(chat);
    }

    @Override
    public MessageDto convertToDto(Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setMessage(message.getMessage());
        messageDto.setSenderId(String.valueOf(message.getSender().getId()));
        messageDto.setChatId(String.valueOf(message.getChat().getId()));
        return messageDto;
    }

    @Override
    public List<MessageDto> convertToDtoList(List<Message> messages) {
        return messages
                .stream()
                .map(this::convertToDto)
                .toList();
    }
}
