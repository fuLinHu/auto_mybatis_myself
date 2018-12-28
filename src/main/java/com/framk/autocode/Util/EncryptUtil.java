package com.framk.autocode.Util;

import com.framk.autocode.entity.User;
import com.framk.autocode.service.UserService;
import com.framk.autocode.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class EncryptUtil {
    private static EncryptUtil encryptUtil;
    public static void encryptPassword(User userEntity){
        String password = userEntity.getPassword();
        password = new BCryptPasswordEncoder().encode(password);
        userEntity.setPassword(password);
    }
    @PostConstruct
    public void init(){
        encryptUtil=this;
    }
    @Autowired
    private UserService userService;

    public static void save(){
        User user = new User();
        user.setPassword("123");
        user.setUsername("fulinhu");
        user.setEmail("1205668006@qq.com");
        user.setTelephone("15718855442");
        EncryptUtil.encryptPassword(user);
        encryptUtil.userService.save(user);
    }
}
