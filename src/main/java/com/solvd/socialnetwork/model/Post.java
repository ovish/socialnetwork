package com.solvd.socialnetwork.model;

import java.time.LocalDateTime;

public class Post {

    private Long id;
    private Long userId;
    private String content;
    private String mediaType;
    private String mediaUrl;
    private LocalDateTime createdDate;

    public Post() {}

    public Post(Long id, Long userId, String content, String mediaType, String mediaUrl, LocalDateTime createdDate) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.mediaType = mediaType;
        this.mediaUrl = mediaUrl;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
