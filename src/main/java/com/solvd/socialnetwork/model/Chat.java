package com.solvd.socialnetwork.model;

import java.time.LocalDateTime;

public class Chat {
    private Long id;
    private String name;
    private LocalDateTime createDate;

    public Chat() {
    }

    public Chat(Long id, String name, LocalDateTime createDate) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
