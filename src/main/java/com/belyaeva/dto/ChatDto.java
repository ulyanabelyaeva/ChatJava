package com.belyaeva.dto;

import java.util.ArrayList;
import java.util.List;

public class ChatDto {

    private String id;

    private UserDto receiver;

    private List<MessageDto> messages = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserDto getReceiver() {
        return receiver;
    }

    public void setReceiver(UserDto receiver) {
        this.receiver = receiver;
    }

    public List<MessageDto> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDto> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "ChatDto{" +
                "id='" + id + '\'' +
                ", receiver=" + receiver +
                ", messages=" + messages +
                '}';
    }
}
