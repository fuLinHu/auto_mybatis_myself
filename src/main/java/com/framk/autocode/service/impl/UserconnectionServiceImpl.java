
package com.framk.autocode.service.impl;

import com.framk.autocode.dao.UserconnectionMapper;
import com.framk.autocode.dao.BaseMapper;
import com.framk.autocode.entity.Userconnection;
import com.framk.autocode.service.UserconnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class UserconnectionServiceImpl extends BaseServiceImpl<Userconnection> implements UserconnectionService {
    @Autowired
    private UserconnectionMapper mapper;
    @Override
    protected BaseMapper<Userconnection, Serializable> getDao() {
        return mapper;
    }


}








