package com.jikim.jblogweb.biz.post;

import com.jikim.jblogweb.biz.common.JDBCUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class PostDAO {

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    private final String INSERT_POST = "insert into post(post_id, category_id, title, content, created_date) " +
            "values(nvl(max(post_id), 0) + 1, ?, ?, ?, now())";
    private final String GET_POST = "select * from post where post_id = ?";
    private final String GET_POST_LIST = "";
    private final String UPDATE_POST = "update post set category_id = ?, title = ?, content = ? where post_id = ?";
    private final String DELETE_POST = "delete from post where post_id = ?";

    public void insertPost(PostVO vo) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(INSERT_POST);
            stmt.setInt(1, vo.getCategoryId());
            stmt.setString(2, vo.getTitle());
            stmt.setString(3, vo.getContent());
            rs = stmt.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
    }

    public PostVO getPost(PostVO vo) {
        PostVO post = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(GET_POST);
            stmt.setInt(1, vo.getPostId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                post = new PostVO();
                post.setPostId(vo.getPostId());
                post.setCategoryId(vo.getCategoryId());
                post.setTitle(vo.getTitle());
                post.setCreatedDate(vo.getCreatedDate());
                post.setContent(vo.getContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return post;
    }

    public void updatePost(PostVO vo) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(UPDATE_POST);
            stmt.setInt(1, vo.getCategoryId());
            stmt.setString(2, vo.getTitle());
            stmt.setString(3, vo.getContent());
            stmt.setInt(4, vo.getPostId());
            rs = stmt.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
    }

    public void deletePost(PostVO vo) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(DELETE_POST);
            stmt.setInt(1, vo.getPostId());
            rs = stmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
    }
}
