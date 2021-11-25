package com.jikim.jblogweb.biz.blog;

import com.jikim.jblogweb.biz.common.JDBCUtil;
import com.jikim.jblogweb.biz.user.UserVO;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BlogDAO {

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    private final String INSERT_BLOG = "insert into blog(blog_id, title, tag, cnt_display_post, status, user_id) values(?, ?, ?, ?, ?, ?)";
    private final String GET_BLOG = "select * from blog where user_id = ?";
    private final String GET_BLOG_LIST_TITLE = "select * from blog where title like ?";
    private final String GET_BLOG_LIST_TAG = "select * from blog where tag like ?";
    private final String GET_BLOG_LIST_USER_NAME = "select * from blog where user_id = (select user_id from blog_user where id like ?)";


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

    public List<BlogVO> getBlogList(BlogVO blogVO) {
        List<BlogVO> blogList = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            if (blogVO.getSearchCondition().equals("TITLE"))
                stmt = conn.prepareStatement(GET_BLOG_LIST_TITLE);
            else if (blogVO.getSearchCondition().equals("TAG"))
                stmt = conn.prepareStatement(GET_BLOG_LIST_TAG);
            else if (blogVO.getSearchCondition().equals("BLOGGER"))
                stmt = conn.prepareStatement(GET_BLOG_LIST_USER_NAME);
            stmt.setString(1, "%" + blogVO.getSearchKeyword() + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                BlogVO blog = new BlogVO();
                blog.setBlogId(rs.getInt("BLOG_ID"));
                blog.setUserId(rs.getInt("USER_ID"));
                blog.setStatus(rs.getString("STATUS"));
                blog.setTag(rs.getString("TAG"));
                blog.setTitle(rs.getString("TITLE"));
                blog.setCntDisplayPost(rs.getInt("CNT_DISPLAY_POST"));
                blogList.add(blog);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return blogList;
    }
}
