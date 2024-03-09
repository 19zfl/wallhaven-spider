package com.zfl19.kernel.jdbc;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * @ClassName:Connection
 * @Description: 获取mysql数据库链接
 * @Author:zfl19
 * @CreateDate:2024/3/9 14:16
 */

public class Connection {

    private static String driver;
    private static String url;
    private static String userName;
    private static String pwd;


    // 注册驱动
    static {
        Properties properties = new Properties();
        try {
            // 读取资源文件
            properties.load(new FileReader("db.properties"));
            // 配置链接信息
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            userName = properties.getProperty("user");
            pwd = properties.getProperty("password");
            Class.forName(driver);
        } catch (IOException e) {
            throw new RuntimeException("读取资源文件失败！");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("链接信息配置失败！");
        }
    }

//    public static java.sql.Connection getConn() {
//        java.sql.Connection connection;
//        try {
//            connection = DriverManager.getConnection(url, userName, pwd);
//        } catch (SQLException e) {
//            throw new RuntimeException("获取链接失败！");
//        }
//        return connection;
//    }

}
