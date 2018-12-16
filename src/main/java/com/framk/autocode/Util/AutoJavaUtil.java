package com.framk.autocode.Util;

public class AutoJavaUtil {
    public static String toJavaEntityProp(String dbParam){
        String[] split = dbParam.split("_");
        String returnStr="";
        int i=0;
        for(String item:split){
            String substring ="";
            if(i!=0){
                substring = item.substring(0, 1);
                substring= substring.toUpperCase()+item.substring(1);
            }else{
                returnStr=item;
            }
            returnStr=returnStr+substring;
            i++;
        }
        return returnStr;
    }
    public static String toJavaEntity(String dbParam){
        String[] split = dbParam.split("_");
        String returnStr="";
        for(String item:split){
            String substring = item.substring(0, 1);
            substring= substring.toUpperCase()+item.substring(1);
            returnStr=returnStr+substring;
        }
        return returnStr;
    }

    public static String getJavaType(String COLUMN_TYPE){
        String javaType="";
        if(COLUMN_TYPE.indexOf("varchar")>=0){
            javaType="String";
        }else if(COLUMN_TYPE.indexOf("int")>=0){
            javaType="Integer";
        }else if(COLUMN_TYPE.indexOf("double")>=0){
            javaType="Double";
        }else if(COLUMN_TYPE.indexOf("date")>=0){
            javaType="Date";
        }
        return javaType;
    }
    public static String getMybatisType(String COLUMN_TYPE){
        String javaType="";
        if(COLUMN_TYPE.indexOf("varchar")>=0){
            javaType="VARCHAR";
        }else if(COLUMN_TYPE.indexOf("int")>=0){
            javaType="INTEGER";
        }else if(COLUMN_TYPE.indexOf("double")>=0){
            javaType="DOUBLE";
        }else if(COLUMN_TYPE.indexOf("date")>=0){
            javaType="TIMESTAMP";
        }
        return javaType;
    }
}
