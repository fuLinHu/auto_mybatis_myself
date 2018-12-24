
package com.framk.autocode.service.impl;

import com.framk.autocode.dao.StudentMapper;
import com.framk.autocode.dao.BaseMapper;
import com.framk.autocode.entity.Student;
import com.framk.autocode.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService {
    @Autowired(required = false)
    private StudentMapper mapper;
    @Override
    protected BaseMapper<Student, Serializable> getDao() {
        return mapper;
    }


}








