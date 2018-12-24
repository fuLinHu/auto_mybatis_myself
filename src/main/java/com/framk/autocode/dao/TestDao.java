package com.framk.autocode.dao;




import com.framk.autocode.entity.Test;

import java.util.List;


public interface TestDao {
    public List<Test> findPageBy(Test test);
    public Test findById(String id);
    public void save(Test test);
    public void update(Test test);
    public void deleteById(String id);
}
