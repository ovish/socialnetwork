package com.solvd.socialnetwork.model;

import java.time.LocalDateTime;

public class Friendship {
    private Long id;
    private Long userFromId;
    private Long userToId;
    private LocalDateTime requestDate;
    private LocalDateTime approveDate;

    public Friendship() {
    }

    public Friendship(Long id, Long userFromId, Long userToId, LocalDateTime requestDate, LocalDateTime approveDate) {
        this.id = id;
        this.userFromId = userFromId;
        this.userToId = userToId;
        this.requestDate = requestDate;
        this.approveDate = approveDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserFromId() {
        return userFromId;
    }

    public void setUserFromId(Long userFromId) {
        this.userFromId = userFromId;
    }

    public Long getUserToId() {
        return userToId;
    }

    public void setUserToId(Long userToId) {
        this.userToId = userToId;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDateTime getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(LocalDateTime approveDate) {
        this.approveDate = approveDate;
    }
}
