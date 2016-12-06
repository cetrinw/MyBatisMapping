package com.cetrinw.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Cetrin Wang on 2016/11/22.
 */
public class FileUtil {

    public static void writeObject(File file, Object obj) {
        try {
            if(!file.exists()){
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileOutputStream fout = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(obj);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            new RuntimeException("Error: 对像输出错误");
        }
    }
}
