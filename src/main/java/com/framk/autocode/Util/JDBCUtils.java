package com.framk.autocode.Util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 数据库连接工具类
 * @author vanguard
 *
 */
public class JDBCUtils {

    private static Connection conn;
    private static String driverClass;
    private static String url;
    private static String username;
    private static String password;
    private static InputStream in;

    static {
        try {
            in = JDBCUtils.class.getClassLoader().getResourceAsStream("application.properties");
            Properties prop = new Properties();
            prop.load(in);
            driverClass = prop.getProperty("driverClass");
            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
            Class.forName(driverClass);
            conn = DriverManager.getConnection(url, username, password);
        } catch (IOException e) {
            System.out.println("加载配置文件失败");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("驱动包加载失败");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
    }

    /**
     * 获取连接的方法
     * @return
     */
    public static Connection getConnection() {
        return conn;
    }

    /**
     * 关闭连接 释放资源
     * @param conn
     * @paramsm
     * @throws SQLException
     */
    public static void close(Connection conn, PreparedStatement ps) throws SQLException {
        if(conn != null) {
            conn.close();
        }
        if(ps != null) {
            ps.close();
        }
    }

    /**
     * 关闭所有连接
     * @param conn
     * @paramst
     * @param rs
     * @throws SQLException
     */
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) throws SQLException {
        if(conn != null) {
            conn.close();
        }
        if(ps != null) {
            ps.close();
        }
        if(rs != null) {
            rs.close();
        }
    }

}