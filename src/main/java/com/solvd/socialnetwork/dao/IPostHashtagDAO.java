package com.solvd.socialnetwork.dao;

import com.solvd.socialnetwork.model.Follow;
import com.solvd.socialnetwork.model.PostHashtag;

import java.util.List;

public interface IPostHashtagDAO {
    PostHashtag getById(Long postId, Long hashtagId);
    void save(PostHashtag postHashtag);
    void deleteById(Long postId, Long hashtagId);
    List<PostHashtag> getAll();
}
