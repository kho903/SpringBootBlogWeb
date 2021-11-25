package com.jikim.jblogweb.biz.category;

import com.jikim.jblogweb.biz.blog.BlogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public void insertCategory(CategoryVO vo) {
        categoryDAO.insertCategory(vo);
    }

    @Override
    public CategoryVO getCategory(CategoryVO vo) {
        return categoryDAO.getCategory(vo);
    }

    @Override
    public List<CategoryVO> getCategoryList(BlogVO vo) {
        return categoryDAO.getCategoryList(vo);
    }

    @Override
    public void deleteCategory(CategoryVO vo) {
        categoryDAO.deleteCategory(vo);
    }

    @Override
    public void updateCategory(CategoryVO vo) {
        categoryDAO.updateCategory(vo);
    }

}
