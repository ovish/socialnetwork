package com.solvd.socialnetwork.model;

import java.time.LocalDateTime;

public class Like {
    private Long id;
    private Long postId;
    private Long commentId;
    private Long userId;
    private LocalDateTime createDate;

    public Like() {}

    public Like(Long id, Long postId, Long commentId, Long userId, LocalDateTime createDate) {
        this.id = id;
        this.postId = postId;
        this.commentId = commentId;
        this.userId = userId;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
