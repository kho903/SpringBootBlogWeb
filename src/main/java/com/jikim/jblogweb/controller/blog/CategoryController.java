package com.jikim.jblogweb.controller.blog;

import com.jikim.jblogweb.biz.blog.BlogService;
import com.jikim.jblogweb.biz.category.CategoryService;
import com.jikim.jblogweb.biz.category.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/blogAdmin/adminCategory/{blogId}")
    public String blogAdminCategoryView(@PathVariable int blogId) {
        return "blogadmin_category";
    }

    @PostMapping("/blogAdmin/adminCategory/{blogId}")
    public String blogAdminCategory(@PathVariable int blogId, CategoryVO categoryVO, Model model) {
        categoryVO.setBlogId(blogId);
        categoryService.insertCategory(categoryVO);
        model.addAttribute("blogId", blogId);
        return "redirect:/blogMain/" + blogId;
    }
}
