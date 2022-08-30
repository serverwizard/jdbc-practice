package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {

    public void executeUpdate(String sql, PreparedStatementSetter pss) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            pss.setPreparedStatement(pstmt);

            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }

    public Object executeQuery(String sql, PreparedStatementSetter pss, RowMapper rowMapper) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            pss.setPreparedStatement(pstmt);

            rs = pstmt.executeQuery();

            Object obj = null;
            if (rs.next()) {
                return rowMapper.mapRow(rs);
            }

            return obj;
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }
}
