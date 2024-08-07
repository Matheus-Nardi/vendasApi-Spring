package com.mafn.service;

import java.util.List;

public interface SimpleCrud<T> {
    
    void save(T entity);
    T findById(Integer id);
    List<T> findAll();
    List<T> findByFilter(T entityFilter);
    void deleteById(Integer id);
    void update(Integer id , T entityToUpdate);
}
