package com.solvd.socialnetwork.dao;

import com.solvd.socialnetwork.model.Post;

import java.util.List;

public interface IPostDAO extends IBaseDAO<Post>{
    List<Post> findByUserId(Long userId);
}
