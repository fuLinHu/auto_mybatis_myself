
package com.framk.autocode.service.impl;

import com.framk.autocode.dao.UserMapper;
import com.framk.autocode.dao.BaseMapper;
import com.framk.autocode.entity.User;
import com.framk.autocode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Autowired
    private UserMapper mapper;
    @Override
    protected BaseMapper<User, Serializable> getDao() {
        return mapper;
    }


}








