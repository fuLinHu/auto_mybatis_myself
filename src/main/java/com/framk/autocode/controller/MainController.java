package com.framk.autocode.controller;

import com.framk.autocode.Util.AutoJavaUtil;
import com.framk.autocode.Util.FreemarkerUtil;
import com.framk.autocode.entity.AutoCodeParam;
import com.framk.autocode.entity.DbInfromation;
import com.framk.autocode.publicmoduel.Entity.Constant;
import com.framk.autocode.publicmoduel.Entity.Entity;
import com.framk.autocode.publicmoduel.Entity.ResultMessage;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.*;

@Controller
public class MainController {
    private static JdbcTemplate jdbcTemplate= new JdbcTemplate();

    private String  dbName;

    @RequestMapping("/index1")
    public String toMain(){
        return "/main.html";
    }

    @RequestMapping("/testDb")
    @ResponseBody
    public ResultMessage testDb(DbInfromation dbInfromation){
        ResultMessage resultMessage = new ResultMessage();
        try{
            dbName = dbInfromation.getDbName();
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
            Connection connection = DataSourceUtils.getConnection(build);
            jdbcTemplate.setDataSource(build);
            connection.close();
        }catch (Exception e){
            resultMessage.setStatus(Constant.STATUS_500);
            resultMessage.setSuccess(false);
            resultMessage.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return resultMessage;
    }
    @RequestMapping("/run")
    @ResponseBody
    public ResultMessage runAutoCode(AutoCodeParam autoCodeParam){
        String path = autoCodeParam.getPath();
        ResultMessage resultMessage = new ResultMessage();
        try{
            String sql="select table_name from information_schema.tables where table_schema='"+dbName+"' and table_type='base table'";
            //格式如下
            /*table_name----------student
            table_name----------test_birthday
            table_name----------userconnection*/
            Map<String,Map<String,String>> map =new HashMap<>();
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
            Map<String,Object>  ma = new HashMap<>();
            for (Map<String, Object> item:maps) {
                Set<String> strings = item.keySet();
                for(String ite:strings){
                    String sourceTableName = (String) item.get(ite);
                    String tableName = AutoJavaUtil.toJavaEntity(sourceTableName);
                    String aliases_table = AutoJavaUtil.toJavaEntityProp(sourceTableName);
                    String sql_table_struck="select column_name,COLUMN_TYPE,COLUMN_KEY from information_schema.columns where table_schema='"+dbName+"' and table_name='"+sourceTableName+"'";
                    List<Map<String, Object>> maps1 = jdbcTemplate.queryForList(sql_table_struck);
                    List<Entity>  list = new ArrayList<>();
                    String COLUMN_LIST ="";
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
                    ma.put("controllerRootPath",aliases_table);
                    ma.put("entityPackage", autoCodeParam.getEntity());
                    ma.put("daoPackage", autoCodeParam.getDao());
                    ma.put("servicePackage", autoCodeParam.getService());
                    ma.put("serviceImplPackage", autoCodeParam.getImpl());
                    ma.put("controllerPackage", autoCodeParam.getController());
                    ma.put("COLUMN_LIST", COLUMN_LIST);//列集合字符串
                    ma.put("table", sourceTableName);
                    ma.put("aliases_table", aliases_table);

                    FreemarkerUtil.runTemplate("entity.ftl",autoCodeParam.getEntity(),tableName+".java" , path,ma);
                    FreemarkerUtil.runTemplate("dao.ftl",autoCodeParam.getDao(),tableName+"Mapper.java" , path,ma);
                    FreemarkerUtil.runTemplate("service.ftl",autoCodeParam.getService(),tableName+"Service.java" , path, ma);
                    FreemarkerUtil.runTemplate("serviceImpl.ftl",autoCodeParam.getImpl(),tableName+"ServiceImpl.java" , path, ma);
                    FreemarkerUtil.runTemplate("controller.ftl",autoCodeParam.getController(),tableName+"Controller.java" , path, ma);
                    FreemarkerUtil.runTemplate("mapperxml.ftl","",tableName+"Mapper.xml" , path, ma);
                }
            }
            FreemarkerUtil.runTemplate("baseEntity.ftl",autoCodeParam.getEntity(),"BaseEntity.java" , path,ma);
            FreemarkerUtil.runTemplate("baseDao.ftl",autoCodeParam.getDao(),"BaseMapper.java" , path,ma);
            FreemarkerUtil.runTemplate("baseService.ftl",autoCodeParam.getService(),"BaseService.java" , path, ma);
            FreemarkerUtil.runTemplate("baseServiceImpl.ftl",autoCodeParam.getImpl(),"BaseServiceImpl.java" , path, ma);
            FreemarkerUtil.runTemplate("application.ftl","","application.yml" , path, ma);
            FreemarkerUtil.runTemplate("pom.ftl","","pom.xml" , path, ma);
        }catch (Exception e){
            resultMessage.setStatus(Constant.STATUS_500);
            resultMessage.setSuccess(false);
            resultMessage.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return resultMessage;
    }
}
