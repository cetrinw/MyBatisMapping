package com.cetrinw.db.mapping;

import com.cetrinw.common.Constants;
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

        if (type.equals(Constants.O_DATE)) {
            data.setColumnClassName(Constants.J_DATE);
        } else if (type.equals(Constants.O_VARCHAR2)) {
            data.setColumnClassName(Constants.J_STRING);
        } else if (type.equals(Constants.O_CHAR)) {
            if (data.getPrecision() > 1) {
                data.setColumnClassName(Constants.J_STRING);
            } else {
                data.setColumnClassName(Constants.J_CHAR);
            }
        } else if (type.equals(Constants.O_NUMBER)) {
            if (data.getScale() > 0) {
                if (data.getScale() < 6) {
                    data.setColumnClassName(Constants.J_FOLAT);
                } else {
                    data.setColumnClassName(Constants.J_DOUBLE);
                }
            } else {
                data.setColumnClassName(Constants.J_INT);
            }
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