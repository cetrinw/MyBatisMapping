package com.cetrinw.db.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Cetrin Wang on 2016/11/16.
 * 获取数据库链接
 */
public class ConnectionUtil {

    private static Logger log = LoggerFactory.getLogger(ConnectionUtil.class);

    private static final String FILE_NAME = "config/db.properties";//这里是指放在classes下，如果有包的话，前面加包名即可。例：/com/web/db.properties
    private Properties properties;//配置文件

    /**
     * 返回单例
     *
     * @return
     */
    public static ConnectionUtil getInstance() {
        return new ConnectionUtil();
    }

    /**
     * 初始化配置文件
     */
    private ConnectionUtil() {
        log.info("获取数据库配置文件...");
        properties = new Properties();

        InputStream input = this.getClass().getClassLoader().getResourceAsStream(FILE_NAME);

        if (input != null) {
            try {
                log.info("获取数据库配置文件成功!");
                properties.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        try {
            log.info("初始化数据库连接...");
            Class.forName(properties.getProperty("jdbc.driver"));
            return DriverManager.getConnection(properties.getProperty("jdbc.url"),
                    properties.getProperty("jdbc.username"),
                    properties.getProperty("jdbc.password"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
