package com.cetrinw.db.base;

import com.cetrinw.db.table.entity.ColumnData;
import com.cetrinw.db.table.entity.TableData;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Cetrin Wang on 2016/11/17.
 */
public class SelectColumnDataTest {


    @Test
    public void getMateInfo() throws Exception {
        TableData tableData = SelectColumnData.getInstance().getMateInfo("pr_project_info");

        for (ColumnData columnData : tableData.getColumnDatas()) {
            System.out.println(columnData.getColumnName()+"    "+columnData.getColumnType()+"    "
                    +columnData.getColumnClassName() +"    "+columnData.getPrecision() +"     "+ columnData.getScale());
        }
    }

    @Test
    public void tableNameConv(){
        String tableName = "PR_PROJECT_INFO";

        tableName = tableName.toLowerCase();

        Pattern pattern = Pattern.compile("_(\\w)");
        Matcher matcher = pattern.matcher(tableName);

        StringBuffer sb = new StringBuffer();

        while (matcher.find()){
            matcher.appendReplacement(sb,matcher.group(1).toUpperCase());
        }

        matcher.appendTail(sb);

        System.out.println(sb.toString());
    }

    @Test
    public void subStr(){
        String name = "PR_PROJECT_INFO";

//        Arrays.

        System.out.println(name.substring(0, 2));
//        name.
    }
}