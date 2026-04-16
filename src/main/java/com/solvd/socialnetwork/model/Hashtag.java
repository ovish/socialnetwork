package com.solvd.socialnetwork.model;

import java.time.LocalDateTime;

public class Hashtag {
    private Long id;
    private String name;

    public Hashtag() {
    }

    public Hashtag(Long id, String name) {
        this.id = id;
        this.name = name;
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

}
