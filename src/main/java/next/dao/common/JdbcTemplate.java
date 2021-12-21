package next.dao.common;

import core.jdbc.ConnectionManager;
import next.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

    public void update(String sql, PreparedStatementSetter preparedStatementSetter) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            preparedStatementSetter.setValue(pstmt);
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

    public List query(String sql,PreparedStatementSetter preparedStatementSetter, RowMapper rowMapper) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            preparedStatementSetter.setValue(pstmt);
            rs = pstmt.executeQuery();
            List<Object> list = new ArrayList<>();
            while (rs.next()){
                list.add(rowMapper.mapRow(rs));
            }
            return list;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public Object queryForObject(String sql,PreparedStatementSetter preparedStatementSetter, RowMapper rowMapper) throws SQLException {
        List list = query(sql,preparedStatementSetter,rowMapper);
        if(list.isEmpty())
            return null;
        return list.get(0);
    }
}
