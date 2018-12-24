package com.framk.autocode.service.impl;


import com.framk.autocode.dao.TestDao;
import com.framk.autocode.entity.Test;
import com.framk.autocode.service.TestService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired(required = false)
    private TestDao testMapper;


    public PageInfo findPageBy(Test test){
        PageHelper.startPage(test.getPageNum(), test.getPageSize());
        List<Test> pageBy = testMapper.findPageBy(test);
        PageInfo pageInfo = new PageInfo(pageBy);
        return pageInfo;
    }

    @Override
    public Test findById(String id) {
        return testMapper.findById(id);
    }

    @Override
    public void save(Test test) {
        testMapper.save(test);
    }

    @Override
    public void update(Test test) {
        testMapper.update(test);
    }

    @Override
    public void deleteById(String id) {
        testMapper.deleteById(id);
    }


    @Override
    public Iterable findAll() {
        //List<Test> all = testDao.findAll();
        return null;
    }


}
