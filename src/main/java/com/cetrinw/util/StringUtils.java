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
}
