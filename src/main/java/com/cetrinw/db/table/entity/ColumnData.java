package com.cetrinw.db.table.entity;

import java.io.Serializable;

/**
 * Created by Cetrin Wang on 2016/11/16.
 */
public class ColumnData implements Serializable{

    private String columnName; //字段名
    private String columnType; //字段类型
    private int precision; //字段长度
    private int scale;//字段小数位
    private String columnClassName;//java映射类型

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getColumnClassName() {
        return columnClassName;
    }

    public void setColumnClassName(String columnClassName) {
        this.columnClassName = columnClassName;
    }
}
