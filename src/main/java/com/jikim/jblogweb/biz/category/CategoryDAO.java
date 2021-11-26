package com.jikim.jblogweb.biz.category;

import com.jikim.jblogweb.biz.blog.BlogVO;
import com.jikim.jblogweb.biz.common.JDBCUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDAO {
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    private String INSERT_CATEGORY = "insert into CATEGORY" +
            "(BLOG_ID, CATEGORY_ID , CATEGORY_NAME, DISPLAY_TYPE, CNT_DISPLAY_POST, DESCRIPTION, CREATED_DATE, MODIFIED_DATE) " +
            "values(?, (select nvl(max(CATEGORY_ID), 0) +1 from CATEGORY), ?, ?, ?, ?, now(), now())";
    private String GET_CATEGORY = "select * from category where category_id = ?";
    private String GET_CATEGORY_LIST = "select * from category where blog_id = ?";
    private String DELETE_CATEGORY = "delete from category where category_id = ?";
    private String UPDATE_CATEGORY = "update category set category_name = ?, display_type = ?, description = ?  where category_name = ?";


    public void insertCategory(CategoryVO vo) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(INSERT_CATEGORY);
            stmt.setInt(1, vo.getBlogId());
            stmt.setString(2, vo.getCategoryName());
            stmt.setString(3, vo.getDisplayType());
            stmt.setInt(4, vo.getCntDisplayPost());
            stmt.setString(5, vo.getDescription());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    public CategoryVO getCategory(CategoryVO vo) {
        CategoryVO category = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(GET_CATEGORY);
            stmt.setInt(1, vo.getCategoryId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                category = new CategoryVO();
                category.setCategoryId(rs.getInt("CATEGORY_ID"));
                category.setCategoryName(rs.getString("CATEGORY_NAME"));
                category.setBlogId(rs.getInt("BLOG_ID"));
                category.setCntDisplayPost(rs.getInt("CNT_DISPLAY_POST"));
                category.setDescription(rs.getString("DESCRIPTION"));
                category.setDisplayType(rs.getString("DISPLAY_TYPE"));
                category.setCreatedDate(rs.getDate("CREATED_DATE"));
                category.setModifiedDate(rs.getDate("MODIFIED_DATE"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return category;
    }

    public List<CategoryVO> getCategoryList(BlogVO vo) {
        List<CategoryVO> categoryList = new ArrayList<>();
        CategoryVO category = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(GET_CATEGORY_LIST);
            stmt.setInt(1, vo.getBlogId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                category = new CategoryVO();
                category.setCategoryId(rs.getInt("CATEGORY_ID"));
                category.setCategoryName(rs.getString("CATEGORY_NAME"));
                category.setBlogId(rs.getInt("BLOG_ID"));
                category.setCntDisplayPost(rs.getInt("CNT_DISPLAY_POST"));
                category.setDescription(rs.getString("DESCRIPTION"));
                category.setDisplayType(rs.getString("DISPLAY_TYPE"));
                category.setCreatedDate(rs.getDate("CREATED_DATE"));
                category.setModifiedDate(rs.getDate("MODIFIED_DATE"));
                categoryList.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return categoryList;
    }

    public void deleteCategory(CategoryVO vo) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(DELETE_CATEGORY);
            stmt.setInt(1, vo.getCategoryId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    public void updateCategory(CategoryVO vo) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(UPDATE_CATEGORY);
            stmt.setString(1, vo.getCategoryName());
            stmt.setString(2, vo.getDisplayType());
            stmt.setString(3, vo.getDescription());
            stmt.setString(4, vo.getCategoryName());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

}
