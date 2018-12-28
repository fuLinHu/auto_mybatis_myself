package com.framk.autocode.config;

import com.framk.autocode.entity.User;
import com.framk.autocode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        User user = new User();
        user.setUsername(username);
        List<User> listBy = userService.findListBy(user);
        if (listBy == null||listBy.size()<=0){
            throw new UsernameNotFoundException("用户不存在！");
        }
        User userEntity=listBy.get(0);
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = createAuthorities("user");
        return new org.springframework.security.core.userdetails.User(userEntity.getUsername(), userEntity.getPassword(), simpleGrantedAuthorities);
    }

    /**
     * 权限字符串转化
     *
     * 如 "USER,ADMIN" -> SimpleGrantedAuthority("USER") + SimpleGrantedAuthority("ADMIN")
     *
     * @param roleStr 权限字符串
     */
    private List<SimpleGrantedAuthority> createAuthorities(String roleStr){
        String[] roles = roleStr.split(",");
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for (String role : roles) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role));
        }
        return simpleGrantedAuthorities;
    }

}
