package com.solvd.socialnetwork.dao;

import com.solvd.socialnetwork.model.Hashtag;
import com.solvd.socialnetwork.model.PostHashtag;

import java.util.List;

public interface IHashtagDAO extends IBaseDAO<Hashtag>{
    List<Hashtag> getAll();
    Hashtag getByName(String name);


    }
