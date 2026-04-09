package com.solvd.socialnetwork.model;

import java.time.LocalDateTime;

public class GroupMember {
    private Long id;
    private Long userId;
    private LocalDateTime joinDate;

    public GroupMember() {
    }

    public GroupMember(Long id, Long userId, LocalDateTime joinDate) {
        this.id = id;
        this.userId = userId;
        this.joinDate = joinDate;
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

    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDateTime joinDate) {
        this.joinDate = joinDate;
    }
}
