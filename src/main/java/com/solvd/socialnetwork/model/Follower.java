package com.solvd.socialnetwork.model;

import java.time.LocalDateTime;

public class Follower {
    private Long followingUserId;
    private Long followedUserId;
    private LocalDateTime createDate;

    public Follower() {
    }

    public Follower(Long followingUserId, Long followedUserId, LocalDateTime createDate) {
        this.followingUserId = followingUserId;
        this.followedUserId = followedUserId;
        this.createDate = createDate;
    }

    public Long getFollowingUserId() {
        return followingUserId;
    }

    public void setFollowingUserId(Long followingUserId) {
        this.followingUserId = followingUserId;
    }

    public Long getFollowedUserId() {
        return followedUserId;
    }

    public void setFollowedUserId(Long followedUserId) {
        this.followedUserId = followedUserId;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
