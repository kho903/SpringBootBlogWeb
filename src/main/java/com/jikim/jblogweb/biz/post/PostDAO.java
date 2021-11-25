package com.jikim.jblogweb.biz.post;

import com.jikim.jblogweb.biz.blog.BlogVO;
import com.jikim.jblogweb.biz.common.JDBCUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class PostDAO {

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    private final String INSERT_POST = "insert into post(post_id, category_id, title, content, created_date) " +
            "values((select nvl(max(post_id), 0) +1 from post), ?, ?, ?, now())";
    private final String GET_POST = "select * from post where post_id = ?";
    private final String GET_POST_LIST = "select * from post as p, category as c where p.category_id = c.category_id and blog_id = ?";
    private final String UPDATE_POST = "update post set category_id = ?, title = ?, content = ? where post_id = ?";
    private final String DELETE_POST = "delete from post where post_id = ?";

    public void insertPost(PostVO vo) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(INSERT_POST);
            stmt.setInt(1, vo.getCategoryId());
            stmt.setString(2, vo.getTitle());
            stmt.setString(3, vo.getContent());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    public List<PostVO > getPostList(BlogVO blogVO) {
        List<PostVO> postList = new ArrayList<>();
        PostVO post = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(GET_POST_LIST);
            stmt.setInt(1, blogVO.getBlogId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                post = new PostVO();
                post.setPostId(rs.getInt("POST_ID"));
                post.setTitle(rs.getString("TITLE"));
                post.setContent(rs.getString("CONTENT"));
                post.setCreatedDate(rs.getDate("CREATED_DATE"));
                post.setCategoryId(rs.getInt("CATEGORY_ID"));
                postList.add(post);
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return postList;
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
                post.setPostId(rs.getInt("POST_ID"));
                post.setTitle(rs.getString("TITLE"));
                post.setContent(rs.getString("CONTENT"));
                post.setCreatedDate(rs.getDate("CREATED_DATE"));
                post.setCategoryId(rs.getInt("CATEGORY_ID"));
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
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
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
