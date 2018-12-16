package com.framk.autocode.mianrun;

import com.framk.autocode.Util.FreemarkerUtil;
import com.framk.autocode.publicmoduel.Entity.Constant;
import freemarker.template.Configuration;
import freemarker.template.Template;


import java.io.*;
import java.util.HashMap;
import java.util.Map;
public class RunMain {
    private static final String TEMPLATE_PATH = "src\\main\\resources\\templates";
    private static final String CLASS_PATH = "src\\main\\resources";


    public static void main(String[] args) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("classPath", "com.freemark.hello");
        dataMap.put("className", "User");
        dataMap.put("Id", "Id");
        dataMap.put("userName", "userName");
        dataMap.put("password","password");

        FreemarkerUtil.runTemplate("entity.ftl",Constant.entity_package,"User.java" , dataMap);



        // step1 创建freeMarker配置实例
       // Configuration configuration = new Configuration();
        Writer out = null;
        try {
            /*// step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            // step3 创建数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("classPath", "com.freemark.hello");
            dataMap.put("className", "User");
            dataMap.put("Id", "Id");
            dataMap.put("userName", "userName");
            dataMap.put("password","password");
            // step4 加载模版文件
            Template template = configuration.getTemplate("entity.ftl");
            // step5 生成数据
            File docFile = new File(CLASS_PATH + "\\" + "User.java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            // step6 输出文件
            template.process(dataMap, out);*/
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^User.java 文件创建成功 !");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
