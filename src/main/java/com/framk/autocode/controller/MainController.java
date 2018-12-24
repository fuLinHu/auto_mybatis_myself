package com.framk.autocode.controller;

import com.framk.autocode.entity.DbInfromation;
import com.framk.autocode.publicmoduel.Entity.Constant;
import com.framk.autocode.publicmoduel.Entity.ResultMessage;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;

@Controller
public class MainController {
    private static JdbcTemplate jdbcTemplate= new JdbcTemplate();

    @RequestMapping("/index")
    public String toMain(){
        return "/main.html";
    }

    @RequestMapping("/testDb")
    public ResultMessage testDb(DbInfromation dbInfromation){
        ResultMessage resultMessage = new ResultMessage();
        try{
            String dbName = dbInfromation.getDbName();
            String dbPort = dbInfromation.getDbPort();
            String dbIp = dbInfromation.getDbIp();
            String dbUrl="jdbc:mysql://"+dbIp+":"+dbPort+"/"+dbName+"?serverTimezone=UTC";
            String password = dbInfromation.getPassword();
            String username = dbInfromation.getUsername();
            DataSourceBuilder builder = DataSourceBuilder.create();
            builder.url(dbUrl);
            builder.driverClassName("com.mysql.cj.jdbc.Driver");
            builder.password(password);
            builder.username(username);
            DataSource build = builder.build();
            //JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(build);
        }catch (Exception e){
            resultMessage.setStatus(Constant.STATUS_500);
            resultMessage.setSuccess(false);
            e.printStackTrace();
        }
        return resultMessage;
    }


}
