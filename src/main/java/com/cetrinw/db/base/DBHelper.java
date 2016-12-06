package com.cetrinw.db.base;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Cetrin Wang on 2016/9/8.
 * 操作DB辅助类
 */
public abstract class DBHelper {

    /**
     * 从数据库连接池获取数据库链接
     *
     * @return
     */
    protected Connection getConnection(boolean autoCommit) {

        try {
            Connection conn = ConnectionUtil.getInstance().getConnection();
            conn.setAutoCommit(autoCommit);

            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected Connection getConnection() {
        return getConnection(true);
    }

    protected PreparedStatement getPreparedStatement(Connection conn,String sql){
        try {
            return conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 释放资源
     */
    protected void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 释放资源
     */
    protected void closePreparedStatement(PreparedStatement pst) {
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
