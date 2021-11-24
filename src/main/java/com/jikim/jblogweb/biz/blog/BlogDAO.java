package com.jikim.jblogweb.biz.blog;

import com.jikim.jblogweb.biz.common.JDBCUtil;
import com.jikim.jblogweb.biz.user.UserVO;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class BlogDAO {

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    private final String INSERT_BLOG = "insert into blog(blog_id, title, tag, cnt_display_post, status, user_id) values(?, ?, ?, ?, ?, ?)";
    private final String GET_BLOG = "select * from blog where user_id = ?";

    public void insertBlog(BlogVO vo, HttpSession session) {
        UserVO user = (UserVO)session.getAttribute("user");
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(INSERT_BLOG);
            stmt.setInt(1, user.getUserId());
            stmt.setString(2, vo.getTitle());
            stmt.setString(3, vo.getTag());
            stmt.setInt(4, 0);
            stmt.setString(5, "PUBLIC");
            stmt.setInt(6, user.getUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // JDBC 5단계 : Connection 종료
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public BlogVO getBlog(UserVO userVO ) {
        BlogVO blog = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(GET_BLOG);
            stmt.setInt(1, userVO.getUserId());
            rs = stmt.executeQuery();

            while (rs.next()) {
                blog = new BlogVO();
                blog.setBlogId(rs.getInt("BLOG_ID"));
                blog.setUserId(rs.getInt("USER_ID"));
                blog.setStatus(rs.getString("STATUS"));
                blog.setTag(rs.getString("TAG"));
                blog.setTitle(rs.getString("TITLE"));
                blog.setCntDisplayPost(rs.getInt("CNT_DISPLAY_POST"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return blog;
    }
}
