package com.solvd.socialnetwork.model;

import java.time.LocalDateTime;

public class Message {
    private Long id;
    private Long senderId;
    private Long chatId;
    private String content;
    private LocalDateTime sentDate;

    public Message() {}

    public Message(LocalDateTime sentDate, String content, Long chatId, Long senderId, Long id) {
        this.sentDate = sentDate;
        this.content = content;
        this.chatId = chatId;
        this.senderId = senderId;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }
}
