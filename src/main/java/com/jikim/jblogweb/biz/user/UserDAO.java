package com.jikim.jblogweb.biz.user;

import com.jikim.jblogweb.biz.common.JDBCUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAO {
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    private String GET_USER = "select * from blog_user where id = ? and password = ?";
    private String GET_USER_LIST = "select * from blog_user";

    public UserVO getUser(UserVO vo) {
        UserVO user = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(GET_USER);
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

    public List<UserVO> getUserList() {
        UserVO user = null;
        List<UserVO> userList = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(GET_USER_LIST);
            rs = stmt.executeQuery();
            while (rs.next()) {
                user = new UserVO();
                user.setUserId(rs.getInt("USER_ID"));
                user.setId(rs.getString("ID"));
                user.setUserName(rs.getString("USER_NAME"));
                user.setRole(rs.getString("ROLE"));
                user.setPassword(rs.getString("PASSWORD"));
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return userList;
    }

    public void insertUser(UserVO vo) {

    }
}
