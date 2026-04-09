package com.solvd.socialnetwork.model;

import java.time.LocalDateTime;

public class Group {
    private Long id;
    private Long adminId;
    private String name;
    private String description;
    private String imageUrl;
    private LocalDateTime createDate;

    public Group() {
    }

    public Group(Long id, Long adminId, String name, String description, String imageUrl, LocalDateTime createDate) {
        this.id = id;
        this.adminId = adminId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
