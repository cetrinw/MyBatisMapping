package com.cetrinw.freemark;

import com.cetrinw.db.base.SelectColumnData;
import com.cetrinw.db.table.entity.TableData;
import com.cetrinw.freemark.output.FillTemplate;


/**
 * Created by Cetrin Wang on 2016/11/29.
 */
public class BootStrap {

    private SelectColumnData selectColumnData;
    private FillTemplate fillTemplate;
    private String tableName;

    public BootStrap(String tableName){
        this.tableName = tableName;
        this.selectColumnData = SelectColumnData.getInstance();
        this.fillTemplate = new FillTemplate();
    }

    /**
     * 构建实体
     */
    public void buildEntity(){
        TableData tableData = this.selectColumnData.getMateInfo(this.tableName);
        this.fillTemplate.fillEntity(tableData);
    }

    /**
     * 构建实体
     */
    public void buildMapper(){
        TableData tableData = this.selectColumnData.getMateInfo(this.tableName);
        this.fillTemplate.fillMapper(tableData);
    }
}
