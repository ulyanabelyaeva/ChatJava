package com.belyaeva.dto;

public class ReadChatRqDto {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ChatRqDto{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
