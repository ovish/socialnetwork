package com.solvd.socialnetwork.model;

import java.time.LocalDateTime;

public class Follow {
    private Long followingId;
    private Long followedId;
    private LocalDateTime createDate;

    public Follow() {
    }

    public Follow(Long followingId, Long followedUserId, LocalDateTime createDate) {
        this.followingId = followingId;
        this.followedId = followedUserId;
        this.createDate = createDate;
    }

    public Long getFollowingId() {
        return followingId;
    }

    public void setFollowingId(Long followingId) {
        this.followingId = followingId;
    }

    public Long getFollowedId() {
        return followedId;
    }

    public void setFollowedId(Long followedId) {
        this.followedId = followedId;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
