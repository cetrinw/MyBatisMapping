package com.cetrinw.db.base;

import com.cetrinw.db.mapping.EntityMapping;
import com.cetrinw.db.table.entity.ColumnData;
import com.cetrinw.db.table.entity.TableData;
import com.cetrinw.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cetrin Wang on 2016/11/16.
 * 查询列信息
 */
public class SelectColumnData extends DBHelper {

    private static Logger log = LoggerFactory.getLogger(SelectColumnData.class);


    private SelectColumnData() {
    }

    public static SelectColumnData getInstance() {
        return new SelectColumnData();
    }

    /**
     * 获取表字段信息
     * @param tableName 表名
     * @return
     */
    public TableData getMateInfo(String tableName) {


        TableData tableData = new TableData();
        List<ColumnData> data = new ArrayList<>();

        Connection conn = getConnection();
        PreparedStatement pst = getPreparedStatement(conn, "SELECT * FROM " + tableName + " WHERE 1 = 2 ");
        try {
            log.info("获取表字段信息...");
            ResultSetMetaData md= pst.getMetaData();

            for (int i = 1; i <= md.getColumnCount() ; i++) {
                ColumnData cd = new ColumnData();

                cd.setColumnName(StringUtils.lineToHump(md.getColumnName(i)));
                cd.setColumnType(md.getColumnTypeName(i));
                cd.setPrecision(md.getPrecision(i));
                cd.setScale(md.getScale(i));
                cd.setColumnClassName(md.getColumnClassName(i));
                EntityMapping.mappingClassType(cd);
                data.add(cd);
            }

            tableData.setColumnDatas(data);
            tableData.setTableName(StringUtils.lineToHump(tableName));
            log.info("获取表字段信息成功!");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(pst);
            closeConnection(conn);
        }
        return tableData;
    }
}
