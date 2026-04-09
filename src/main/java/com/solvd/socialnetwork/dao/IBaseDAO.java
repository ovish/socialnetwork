package com.solvd.socialnetwork.dao;

public interface IBaseDAO <T> {

    T getById(Long id);

    void save(T entity);

    void update(T entity);

    void deleteById(Long id);
}
