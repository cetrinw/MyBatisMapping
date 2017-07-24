package com.cetrinw.freemark.output;

import com.cetrinw.common.Config;
import com.cetrinw.common.FillConstants;
import com.cetrinw.db.table.entity.TableData;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cetrin Wang on 2016/11/29.
 * 填充模板
 */
public class FillTemplate {

    private static Logger log = LoggerFactory.getLogger(FillTemplate.class);

    Config config;
    Configuration configuration;

    public FillTemplate() {
        config = Config.getInstance();
        this.loadConfiguration();
    }

    private void loadConfiguration() {
        try {

            log.info("初始化模版信息");

            configuration = new Configuration();
            configuration.setDirectoryForTemplateLoading(new File(ClassLoader.getSystemClassLoader().getResource("template").getPath()));
            configuration.setObjectWrapper(new DefaultObjectWrapper());
            configuration.setDefaultEncoding("UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 填充实体
     */
    public void fillEntity(TableData data) {

        Map<String, Object> fillMap = new HashMap<>();

        String path = File.separator + FillConstants.ENTITY + File.separator;

        String output = config.outputPath + path + File.separator + data.getTableName() + ".java";

        fillMap.put("tableData", data);
        fillMap.put("author", config.author);
        fillMap.put("now", new Date());
        fillMap.put("packageName", "");

        fillTemplate(path + "${tableData.tableName}.java.fml", output, fillMap);
    }

    public void fillMapper(TableData data) {
        Map<String, Object> fillMap = new HashMap<>();

        String path = File.separator + FillConstants.MAPPER + File.separator;


        String output = config.outputPath + path + data.getTableName() + "Mapper.xml";

        fillMap.put("tableData", data);
        fillMap.put("namespace", "");
        fillMap.put("entityPath", "");

        fillTemplate(path + "${tableData.tableName}Mapper.xml.fml", output, fillMap);
    }

    private void fillTemplate(String templatePath, String outputFilePath, Map<String, Object> fillMap) {

        log.info("填充实体开始...");
        log.info("templatePath : " + templatePath);
        log.info("outputFilePath : " + outputFilePath);


        Template template = null;
        try {
            template = configuration.getTemplate(templatePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File f = new File(outputFilePath);

        File parent =  f.getParentFile();
        if (!parent.exists()) {
            parent.mkdir();
        }

        try {
            template.process(fillMap, new OutputStreamWriter(new FileOutputStream(outputFilePath)));
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("填充实体完成!");
    }
}
