package com.belyaeva.converter.impl;

import com.belyaeva.converter.api.ChatConverter;
import com.belyaeva.converter.api.MessageConverter;
import com.belyaeva.converter.api.UserConverter;
import com.belyaeva.dto.ChatDto;
import com.belyaeva.dto.UserDto;
import com.belyaeva.model.Chat;
import com.belyaeva.model.Message;
import com.belyaeva.model.User;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Component
public class ChatConverterImpl implements ChatConverter {

    private final MessageConverter messageConverter;
    private final UserConverter userConverter;

    public ChatConverterImpl(MessageConverter messageConverter,
                             UserConverter userConverter) {
        this.messageConverter = messageConverter;
        this.userConverter = userConverter;
    }

    @Override
    public ChatDto convertToDto(Chat chat, User currentUser) {
        ChatDto chatDto = new ChatDto();
        chatDto.setId(String.valueOf(chat.getId()));
        List<User> users = chat.getUsers().stream()
                .filter(u -> !Objects.equals(u.getId(), currentUser.getId())).toList();
        User receiver = users.get(0); //todo
        UserDto receiverDto = userConverter.createUserDto(receiver);
        chatDto.setReceiver(receiverDto);
        List<Message> messages = chat.getMessages().stream()
                .sorted(Comparator.comparing(Message::getCreatedAt)).toList();
        chatDto.setMessages(messageConverter.convertToDtoList(messages));
        return chatDto;
    }

    @Override
    public List<ChatDto> convertToDtoList(List<Chat> chats, User currentUser) {
        return chats.stream()
                .map(c -> convertToDto(c, currentUser))
                .toList();
    }
}
