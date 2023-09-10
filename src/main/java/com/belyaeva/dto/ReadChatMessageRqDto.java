package com.belyaeva.dto;

public final class ReadChatMessageRqDto {

    private String senderId;

    private String receiverId;

    public String getSenderId() {
        return senderId;
    }

    public ReadChatMessageRqDto setSenderId(String senderId) {
        this.senderId = senderId;
        return this;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public ReadChatMessageRqDto setReceiverId(String receiverId) {
        this.receiverId = receiverId;
        return this;
    }

    @Override
    public String toString() {
        return "ReadChatMessageRqDto{" +
                "senderId='" + senderId + '\'' +
                ", receiverId='" + receiverId + '\'' +
                '}';
    }
}
