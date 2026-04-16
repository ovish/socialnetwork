package com.solvd.socialnetwork.dao;

import com.solvd.socialnetwork.model.Follow;

public interface IFollowDAO {
    void follow(Follow f);
    void unfollow(Long followedId, Long followingId);
    Follow get(Long followedId, Long followingId);
}
