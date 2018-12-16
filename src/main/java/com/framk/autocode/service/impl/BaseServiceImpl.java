package com.framk.autocode.service.impl;

import com.framk.autocode.dao.BaseMapper;
import com.framk.autocode.service.BaseService;

import java.io.Serializable;
import java.util.List;


public abstract  class BaseServiceImpl<T> implements BaseService<T,Serializable> {
    protected abstract BaseMapper<T,Serializable> getDao();
    @Override
    public void save(T t) {
        getDao().save(t);
    }

    @Override
    public void update(T t) {
        getDao().update(t);
    }

    @Override
    public void saveList(List<T> t) {
        getDao().saveList(t);
    }

    @Override
    public void updateList(List<T> t) {
        getDao().updateList(t);
    }

    @Override
    public T findById(String id) {
        T byId = getDao().findById(id);
        return byId;
    }

    @Override
    public List<T> findListBy(T t) {
        List<T> listBy = getDao().findListBy(t);
        return listBy;
    }

    @Override
    public List<T> findByList(List<T> t) {
        List<T> byList = getDao().findByList(t);
        return byList;
    }

    @Override
    public void deleteById(String id) {
        getDao().deleteById(id);
    }
}
