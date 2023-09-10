package com.belyaeva.service.impl;

import com.belyaeva.converter.api.ChatConverter;
import com.belyaeva.converter.api.MessageConverter;
import com.belyaeva.dto.ChatDto;
import com.belyaeva.dto.MessageDto;
import com.belyaeva.dto.ReadChatRqDto;
import com.belyaeva.model.Chat;
import com.belyaeva.model.Message;
import com.belyaeva.model.User;
import com.belyaeva.repository.ChatRepository;
import com.belyaeva.service.api.ChatService;
import com.belyaeva.service.api.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    private final UserService userService;
    private final MessageConverter messageConverter;
    private final ChatConverter chatConverter;
    private final ChatRepository chatRepository;

    public ChatServiceImpl(UserService userService,
                           MessageConverter messageConverter,
                           ChatConverter chatConverter,
                           ChatRepository chatRepository) {
        this.userService = userService;
        this.messageConverter = messageConverter;
        this.chatConverter = chatConverter;
        this.chatRepository = chatRepository;
    }

    @Override
    @Transactional
    public List<ChatDto> readChats() {
        User user = userService.getCurrentUser();
        List<Chat> chats = chatRepository.findByUsersContains(user);
        User currentUser = userService.getCurrentUser();
        return chatConverter.convertToDtoList(chats, currentUser);
    }

    @Override
    @Transactional
    public void saveMessage(MessageDto messageDto) {
        Optional<Chat> chatOptional = chatRepository.findById(Long.parseLong(messageDto.getChatId()));
        if (chatOptional.isEmpty()){
            return;
        }
        Chat chat = chatOptional.get();

        User user = userService.getUserById(Long.parseLong(messageDto.getSenderId()));
        Message message = messageConverter.convertFromDto(messageDto, user, chat);
        chat.getMessages().add(message);
    }
}
