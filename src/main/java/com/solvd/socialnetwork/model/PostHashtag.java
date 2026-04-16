package com.solvd.socialnetwork.model;

public class PostHashtag {
    private Long postId;
    private Long hashtagId;

    public PostHashtag() {
    }

    public PostHashtag(Long hashtagId, Long id) {
        this.hashtagId = hashtagId;
        this.postId = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getHashtagId() {
        return hashtagId;
    }

    public void setHashtagId(Long hashtagId) {
        this.hashtagId = hashtagId;
    }
}
