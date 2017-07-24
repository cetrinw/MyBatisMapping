package com.cetrinw.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Cetrin Wang on 2016/12/6.
 * 字符串操作类
 */
public class StringUtils {

    /**
     * 下划线命名法转成驼峰命名法
     * @return
     */
    public static String lineToHump(String str){


        Pattern pattern = Pattern.compile("_(\\w)");
        Matcher matcher = pattern.matcher(str.toLowerCase());

        StringBuffer sb = new StringBuffer();

        while (matcher.find()){
            matcher.appendReplacement(sb,matcher.group(1).toUpperCase());
        }

        matcher.appendTail(sb);

        return  sb.toString();
    }

    /**
     * 首字母大写
     * @param name
     * @return
     */
    public static String captureName(String name) {
        //     name = name.substring(0, 1).toUpperCase() + name.substring(1);
//        return  name;
        char[] cs=name.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }

    /**
     * 转换为类名
     * @param name
     * @return
     */
    public static String converClassName(String name){
        return captureName(lineToHump(name));
    }
}
