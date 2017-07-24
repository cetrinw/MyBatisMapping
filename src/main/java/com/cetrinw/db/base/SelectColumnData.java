package com.cetrinw.db.base;

import com.cetrinw.db.mapping.EntityMapping;
import com.cetrinw.db.table.entity.ColumnData;
import com.cetrinw.db.table.entity.TableData;
import com.cetrinw.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
     *
     * @param tableName 表名
     * @return
     */
    public TableData getMateInfo(String tableName) {

        TableData tableData = new TableData();
        List<ColumnData> data = new ArrayList<>();

        String findSQL = "select * from (select distinct a.column_name,a.comments,nvl(b.DATA_PRECISION,b.DATA_LENGTH) as data_length,nvl(b.DATA_SCALE,0) as DATA_SCALE,b.DATA_TYPE,\n" +
                "DECODE(A.column_name,cu.column_name,1,0)  as isPrimaryKey,COLUMN_ID\n" +
                "from user_col_comments a left join all_tab_columns  b on a.table_name = b.TABLE_NAME and a.column_name = b.COLUMN_NAME \n" +
                "left join user_cons_columns cu on cu.table_name = a.table_name and cu.column_name = a.column_name\n" +
                "left join user_constraints au on  cu.constraint_name = au.constraint_name and au.constraint_type = 'P'\n" +
                "where a.table_name =? " +
                ") t ORDER BY t.COLUMN_ID";

        Connection conn = getConnection();
        PreparedStatement pst = getPreparedStatement(conn, findSQL);
        ResultSet rs;
        try {
            log.info("findSQL: " + findSQL);
            log.info("获取表字段信息...");
            pst.setString(1, tableName.toUpperCase());

            rs = pst.executeQuery();

            while (rs.next()) {
                ColumnData cd = new ColumnData();

                cd.setDbColumnName(rs.getString("column_name"));
                cd.setColumnName(StringUtils.lineToHump(rs.getString("column_name")));
                cd.setColumnType(rs.getString("DATA_TYPE"));
                cd.setPrecision(rs.getInt("data_length"));
                cd.setScale(rs.getInt("DATA_SCALE"));
                cd.setColumnClassName(rs.getString("DATA_TYPE"));
                cd.setComment(rs.getString("comments"));
                cd.setAsPrimaryKey(rs.getInt("isPrimaryKey"));
                //jdbcType --> javaType
                EntityMapping.mappingClassType(cd);
                data.add(cd);
            }

            /*for (int i = 1; i <= md.getColumnCount(); i++) {
                ColumnData cd = new ColumnData();

                cd.setColumnName(StringUtils.lineToHump(md.getColumnName(i)));
                cd.setColumnType(md.getColumnTypeName(i));
                cd.setPrecision(md.getPrecision(i));
                cd.setScale(md.getScale(i));
                cd.setColumnClassName(md.getColumnClassName(i));
                EntityMapping.mappingClassType(cd);
                data.add(cd);
            }*/

            tableData.setColumnDatas(data);
            tableData.setTableName(StringUtils.converClassName(tableName));
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
