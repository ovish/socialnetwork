package com.solvd.socialnetwork.model;

import java.time.LocalDateTime;

public class Story {
    private Long id;
    private Long userId;
    private String mediaUrl;
    private LocalDateTime CreateDate;
    private LocalDateTime ExpireDate;

    public Story() {}

    public Story(Long id, Long userId, String mediaUrl, LocalDateTime createDate, LocalDateTime expireDate) {
        this.id = id;
        this.userId = userId;
        this.mediaUrl = mediaUrl;
        CreateDate = createDate;
        ExpireDate = expireDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public LocalDateTime getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        CreateDate = createDate;
    }

    public LocalDateTime getExpireDate() {
        return ExpireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        ExpireDate = expireDate;
    }
}
