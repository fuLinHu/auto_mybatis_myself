package com.framk.autocode.Util;

import com.framk.autocode.publicmoduel.Entity.Constant;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class FreemarkerUtil {
    private static final String TEMPLATE_PATH = "src\\main\\resources\\templates";
    private static final String MAPPERXML_PATH = "\\src\\main\\resources\\mapper";
    private static final String CLASS_PATH = "\\src\\main\\java\\";

    /**
     * @param templateName 模板名称这个是提前定义好的
     * @param packageName 包名称
     * @param className 类的名称
     * @param dataMap 需要往模板中填充的数据
     */
    public static void runTemplate(String templateName,String packageName,String className,Map<String,Object> dataMap){
        dataMap.put("package", packageName);
        String rootPath="";
        //F:\代码\aw_weixin\02_开发代码\autocode\src\main\resources\mapper
        try {
            String path = FreemarkerUtil.class.getResource("/").toURI().getPath();
            String[] split = path.split("/target/classes/");
            rootPath=split[0];
            System.out.println(split[0]+"--------------------------");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String[] split = packageName.split("\\.");
        String pathName="";
        if("mapperxml.ftl".equals(templateName)){
            pathName=MAPPERXML_PATH;
        }else{
            pathName=CLASS_PATH;
        }
        for(String item:split){
            pathName=pathName+"/"+item;
        }
        pathName=rootPath+pathName;
        // step1 创建freeMarker配置实例
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            // step4 加载模版文件
            Template template = configuration.getTemplate(templateName);
            // step5 生成数据
            File docFile = new File(pathName );
            if(!docFile.exists()){
                docFile.mkdirs();
            }
            docFile = new File(pathName+ "\\" + className );
            //+ "\\" + className
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            // step6 输出文件
            template.process(dataMap, out);
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^"+className+" 文件创建成功 !");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                    out.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
