package demo;

import com.cetrinw.freemark.BootStrap;

/**
 * Created by Cetrin Wang on 2016/11/16.
 * 创建实体类
 */
public class DemoCreateEntity {

    public static void main(String[] args) {
        BootStrap bootstrap = new BootStrap("PR_PROJECT_INFO");

        bootstrap.buildEntity();
    }
}
