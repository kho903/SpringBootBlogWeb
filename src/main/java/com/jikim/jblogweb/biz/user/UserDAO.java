package com.jikim.jblogweb.biz.user;

import com.jikim.jblogweb.biz.common.JDBCUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class UserDAO   {
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    private String USER_GET = "select * from blog_user where id = ? and password = ?";

    public UserVO getUser(UserVO vo) {
        UserVO user = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USER_GET);
            stmt.setString(1, vo.getId());
            stmt.setString(2, vo.getPassword());
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new UserVO();
                user.setUserId(rs.getInt("USER_ID"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setUserName(rs.getString("USER_NAME"));
                user.setRole(rs.getString("ROLE"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return user;
    }

    public void insertUser(UserVO vo) {

    }
}
