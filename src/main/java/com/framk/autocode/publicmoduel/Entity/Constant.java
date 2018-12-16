package com.framk.autocode.publicmoduel.Entity;

/**
 * 项目的常量值放在该类中
 * 能提出来的常量值一定写在这里面  不能再页面上写字符串
 */
public class Constant {
    public static final String dao_package="com.framk.autocode.dao";
    public static final String service_package="com.framk.autocode.service";
    public static final String serviceimpl_package="com.framk.autocode.service.impl";
    public static final String controller_package="com.framk.autocode.controller";
    public static final String entity_package="com.framk.autocode.entity";
    public static final String dbname="test";



    public static final String  STATUS_404="404";
    public static final String  STATUS_200="200";
    public static final String  STATUS_500="500";

    public static final boolean TREU=true;
    public static final boolean FALSE=false;

    //-------------------回调的时候显示的状态码
    public static final String RESULT_STATUS_0="0";//正在发送！
    public static final String RESULT_STATUS_1="1";//发送成功！
    public static final String RESULT_STATUS_2="2";//发送失败！

    public static final String RESULT_STATUS_0_VALUE="正在发送";//正在发送！
    public static final String RESULT_STATUS_1_VALUE="发送成功";//发送成功！
    public static final String RESULT_STATUS_2_VALUE="发送失败";//发送失败！
    //100:网站  200：小程序 300:公众号
    public static final String MODEL_100="100";
    public static final String MODEL_200="200";
    public static final String MODEL_300="300";

}
