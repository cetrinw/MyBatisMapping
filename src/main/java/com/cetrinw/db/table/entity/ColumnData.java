package com.cetrinw.db.table.entity;

import java.io.Serializable;

/**
 * Created by Cetrin Wang on 2016/11/16.
 */
public class ColumnData implements Serializable {

    private String dbColumnName; //数据库字段名
    private String columnName; //字段名
    private String columnType; //字段类型
    private int precision; //字段长度
    private int scale;//字段小数位
    private String columnClassName;//java映射类型
    private String comment;//描述信息
    private int asPrimaryKey;//是否为主键
    private String mybatisType;//mybatis JDBCTYPE映射

    public String getDbColumnName() {
        return dbColumnName;
    }

    public void setDbColumnName(String dbColumnName) {
        this.dbColumnName = dbColumnName;
    }

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getAsPrimaryKey() {
        return asPrimaryKey;
    }

    public void setAsPrimaryKey(int asPrimaryKey) {
        this.asPrimaryKey = asPrimaryKey;
    }

    public String getMybatisType() {
        return mybatisType;
    }

    public void setMybatisType(String mybatisType) {
        this.mybatisType = mybatisType;
    }
}
