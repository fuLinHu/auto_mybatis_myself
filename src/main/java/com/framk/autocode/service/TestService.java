package com.framk.autocode.service;


import com.framk.autocode.entity.Test;
import com.github.pagehelper.PageInfo;

public interface TestService {
    public Iterable findAll();

    public PageInfo findPageBy(Test test);
    public Test findById(String id);
    public void save(Test test);
    public void update(Test test);
    public void deleteById(String id);






}
