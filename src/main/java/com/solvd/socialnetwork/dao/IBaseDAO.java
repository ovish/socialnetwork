package com.solvd.socialnetwork.dao;

public interface IBaseDAO <T> {

    T getById(int id);

    void save(T entity);

    void update(T entity);

    void deleteById(int id);
}
