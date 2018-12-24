package com.framk.autocode.controller;

import com.framk.autocode.Util.AutoJavaUtil;
import com.framk.autocode.Util.FreemarkerUtil;
import com.framk.autocode.publicmoduel.Entity.Constant;
import com.framk.autocode.publicmoduel.Entity.Entity;
import com.framk.autocode.publicmoduel.Entity.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Controller
public class RunController {





    @Autowired(required = false)
    private JdbcTemplate jdbcTemplate;

    private String dao="com.mytest.dao";
    private String service="com.mytest.service";
    private String serviceimpl="com.mytest.service.impl";
    private String controller="com.mytest.controller";
    private String entity="com.mytest.entity";
    private String dbname="test";




    @RequestMapping(value = "/run")
    @ResponseBody
    public ResultMessage runMain(){
        String sql="select table_name from information_schema.tables where table_schema='test' and table_type='base table'";
        //格式如下
        /*table_name----------student
        table_name----------test_birthday
        table_name----------userconnection*/
        Map<String,Map<String,String>> map =new HashMap<>();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        Map<String,Object>  ma = new HashMap<>();
        String COLUMN_LIST ="";
        for (Map<String, Object> item:maps) {
            Set<String> strings = item.keySet();
            for(String ite:strings){
                String sourceTableName = (String) item.get(ite);
                String tableName = AutoJavaUtil.toJavaEntity(sourceTableName);
                String aliases_table = AutoJavaUtil.toJavaEntityProp(sourceTableName);
                String sql_table_struck="select column_name,COLUMN_TYPE,COLUMN_KEY from information_schema.columns where table_schema='test' and table_name='"+sourceTableName+"'";
                List<Map<String, Object>> maps1 = jdbcTemplate.queryForList(sql_table_struck);
                List<Entity>  list = new ArrayList<>();
                for(Map<String, Object> it:maps1){
                    String column_name = (String)it.get("column_name");
                    COLUMN_LIST=COLUMN_LIST+","+column_name;
                    String COLUMN_TYPE = (String)it.get("COLUMN_TYPE");
                    String MYBATIS_COLUMN_TYPE=AutoJavaUtil.getMybatisType(COLUMN_TYPE);
                    String COLUMN_KEY = (String)it.get("COLUMN_KEY");
                    String java_column_name = AutoJavaUtil.toJavaEntityProp(column_name);
                    String java_column_name_u = AutoJavaUtil.toJavaEntity(column_name);
                    String javaType = AutoJavaUtil.getJavaType(COLUMN_TYPE);
                    Entity entity = new Entity();
                    entity.setJavaName(java_column_name);
                    entity.setJavaType(javaType);
                    entity.setMybatisColumnType(MYBATIS_COLUMN_TYPE);
                    entity.setColumn_name(column_name);
                    if(COLUMN_KEY!=null&&!"".equals(COLUMN_KEY)){
                        entity.setIfKey(true);
                        ma.put("db_id",column_name);
                        ma.put("java_id",java_column_name);
                    }else{
                        entity.setIfKey(false);
                    }
                    entity.setGetName("get"+java_column_name_u);
                    entity.setSetName("set"+java_column_name_u);
                    entity.setServiceName(java_column_name_u+"Service");
                    entity.setServiceImplName(java_column_name_u+"ServiceImpl");
                    entity.setControllerName(java_column_name_u+"Controller");
                    entity.setDbName(column_name);
                    entity.setDbType(COLUMN_TYPE);

                    list.add(entity);
                }
                if(COLUMN_LIST!=null&&!"".equals(COLUMN_LIST)){
                    COLUMN_LIST = COLUMN_LIST.substring(1);
                }
                ma.put("list", list);
                ma.put("className", tableName);
                ma.put("dao",tableName+"Mapper");
                ma.put("service",tableName+"Service");
                ma.put("serviceImpl",tableName+"ServiceImpl");
                ma.put("controller",tableName+"Controller");
                ma.put("entityPackage", Constant.entity_package);
                ma.put("daoPackage", Constant.dao_package);
                ma.put("servicePackage", Constant.service_package);
                ma.put("serviceImplPackage", Constant.serviceimpl_package);
                ma.put("controllerPackage", Constant.controller_package);
                ma.put("COLUMN_LIST", COLUMN_LIST);//列集合字符串
                ma.put("table", sourceTableName);
                ma.put("aliases_table", aliases_table);

                FreemarkerUtil.runTemplate("entity.ftl",Constant.entity_package,tableName+".java" , ma);
                FreemarkerUtil.runTemplate("dao.ftl",Constant.dao_package,tableName+"Mapper.java" , ma);
                FreemarkerUtil.runTemplate("service.ftl",Constant.service_package,tableName+"Service.java" , ma);
                FreemarkerUtil.runTemplate("serviceImpl.ftl",Constant.serviceimpl_package,tableName+"ServiceImpl.java" , ma);
                FreemarkerUtil.runTemplate("mapperxml.ftl","",tableName+"Mapper.xml" , ma);
            }
        }
        //数据库中test 中test_birthday表的结构
        //格式如下
        /*column_name----------id
        COLUMN_TYPE----------int(11)
        COLUMN_KEY----------PRI*/
        return new ResultMessage();
    }
    private void print( List<Map<String, Object>> maps){
        for (Map<String, Object> item:maps) {
            Set<String> strings = item.keySet();
            for(String ite:strings){
                System.out.println(ite+"----------"+item.get(ite));
            }
        }
    }

    private String toJavaEntity(String dbParam){
        String[] split = dbParam.split("_");
        String returnStr="";
        for(String item:split){
            String substring = item.substring(0, 1);
            substring= substring.toUpperCase()+item.substring(1);
            returnStr=returnStr+substring;
        }
        return returnStr;
    }
}
