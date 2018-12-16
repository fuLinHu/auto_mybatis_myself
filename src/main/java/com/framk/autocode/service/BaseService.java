package com.framk.autocode.service;

import java.util.List;

public interface BaseService<T, Serializable> {
    public void save(T t);
    public void update(T t);
    public void saveList(List<T> t);
    public void updateList(List<T> t);
    public T findById(String id);
    public List<T> findListBy(T t);
    public List<T> findByList(List<T> t);
    public void deleteById(String id);
}
