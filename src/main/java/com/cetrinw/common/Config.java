package com.cetrinw.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Cetrin Wang on 2016/11/17.
 * 读取资源文件配置
 */
public class Config {


    private static Logger log = LoggerFactory.getLogger(Config.class);

    private Properties properties;
    private static final String FILE_NAME = "config/config.properties";

    public String outputPath;
    public String author;

    public static Config getInstance(){
        return new Config();
    }


    /**
     * 初始化配置文件
     */
    private Config() {
        log.info("开始加载配置文件...");
        properties = new Properties();

        InputStream input = this.getClass().getClassLoader().getResourceAsStream(FILE_NAME);

        if (input != null) {
            try {
                properties.load(input);
                this.outputPath = properties.getProperty("output.path");
                this.author = properties.getProperty("author");
                log.info("加载配置文件完成!");
            } catch (IOException e) {
                log.error("加载配置失败!");
                log.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

}
