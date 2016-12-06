package com.cetrinw.db.table.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Cetrin Wang on 2016/11/24.
 * 表信息实体
 */
public class TableData implements Serializable{

    private String tableName;//表名
    private String tableDesc;//表描述
    private List<ColumnData> columnDatas;//字段信息

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableDesc() {
        return tableDesc;
    }

    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc;
    }

    public List<ColumnData> getColumnDatas() {
        return columnDatas;
    }

    public void setColumnDatas(List<ColumnData> columnDatas) {
        this.columnDatas = columnDatas;
    }
}
