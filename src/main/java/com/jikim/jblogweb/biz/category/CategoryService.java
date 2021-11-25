package com.jikim.jblogweb.biz.category;

import java.util.List;

public interface CategoryService {
    void insertCategory(CategoryVO vo);

    CategoryVO getCategory(CategoryVO vo);

    List<CategoryVO> getCategoryList(CategoryVO vo);

    void deleteCategory(CategoryVO vo);

    void updateCategory(CategoryVO vo);
}
