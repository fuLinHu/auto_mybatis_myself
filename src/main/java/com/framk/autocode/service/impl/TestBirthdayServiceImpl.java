
package com.framk.autocode.service.impl;

import com.framk.autocode.dao.TestBirthdayMapper;
import com.framk.autocode.dao.BaseMapper;
import com.framk.autocode.entity.TestBirthday;
import com.framk.autocode.service.TestBirthdayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class TestBirthdayServiceImpl extends BaseServiceImpl<TestBirthday> implements TestBirthdayService {
    @Autowired(required = false)
    private TestBirthdayMapper mapper;
    @Override
    protected BaseMapper<TestBirthday, Serializable> getDao() {
        return mapper;
    }


}








