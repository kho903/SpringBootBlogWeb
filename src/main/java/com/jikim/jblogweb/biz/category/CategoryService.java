package com.jikim.jblogweb.biz.category;

import com.jikim.jblogweb.biz.blog.BlogVO;

import java.util.List;

public interface CategoryService {
    void insertCategory(CategoryVO vo);

    CategoryVO getCategory(CategoryVO vo);

    List<CategoryVO> getCategoryList(BlogVO vo);

    void deleteCategory(CategoryVO vo);

    void updateCategory(CategoryVO vo);
}
