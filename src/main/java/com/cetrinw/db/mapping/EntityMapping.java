package com.cetrinw.db.mapping;

import com.cetrinw.common.DBConstants;
import com.cetrinw.db.table.entity.ColumnData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cetrin Wang on 2016/11/17.
 */
public class EntityMapping {

    /**
     * 对ResultSetMetaData对应错误的进行校正
     *
     * @param data
     * @return
     */
    public static void mappingClassType(ColumnData data) {

        String type = data.getColumnType();

        if (type.equalsIgnoreCase(DBConstants.O_DATE)) {
            data.setColumnClassName(DBConstants.J_DATE);
        } else if (type.equalsIgnoreCase(DBConstants.O_VARCHAR2)) {
            data.setColumnClassName(DBConstants.J_STRING);
            data.setMybatisType(DBConstants.M_VARCHAR);
        } else if (type.equalsIgnoreCase(DBConstants.O_CHAR)) {
            if (data.getPrecision() > 1) {
                data.setColumnClassName(DBConstants.J_STRING);
            } else {
                data.setColumnClassName(DBConstants.J_CHAR);
            }
        } else if (type.equalsIgnoreCase(DBConstants.O_NUMBER)) {
            if (data.getScale() > 0) {
                if (data.getScale() < 6) {
                    data.setColumnClassName(DBConstants.J_FOLAT);
                } else {
                    data.setColumnClassName(DBConstants.J_DOUBLE);
                }
            } else {
                data.setColumnClassName(DBConstants.J_INT);
            }
            data.setMybatisType(DBConstants.M_DECIMAL);
        }else{
            data.setColumnClassName(type);
            data.setMybatisType(type);
        }
    }

    /**
     * 批量
     * @param datas
     * @return
     */
    public static void mappingClassType(List<ColumnData> datas){

        List<ColumnData> newList = new ArrayList<>();

        for (ColumnData data : datas) {
            mappingClassType(data);
            newList.add(data);
        }
    }

}