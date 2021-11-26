package com.jikim.jblogweb.controller.blog;

import com.jikim.jblogweb.biz.blog.BlogService;
import com.jikim.jblogweb.biz.blog.BlogVO;
import com.jikim.jblogweb.biz.category.CategoryService;
import com.jikim.jblogweb.biz.category.CategoryVO;
import com.jikim.jblogweb.biz.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/blogAdmin/adminCategory/{blogId}")
    public String blogAdminCategoryView(@PathVariable int blogId, Model model, HttpSession session) {
        UserVO user = (UserVO) session.getAttribute("user");
        BlogVO blog = blogService.getBlog(user);
        List<CategoryVO> categoryList = categoryService.getCategoryList(blog);
        model.addAttribute("categoryList", categoryList);
        return "blogadmin_category";
    }

    @PostMapping("/blogAdmin/adminCategory/{blogId}")
    public String blogAdminCategory(@PathVariable int blogId, CategoryVO categoryVO, Model model) {
        categoryVO.setBlogId(blogId);
        categoryService.insertCategory(categoryVO);
        model.addAttribute("blogId", blogId);
        return "redirect:/blogAdmin/adminCategory/" + blogId;
    }

    @RequestMapping("/blogAdmin/deleteCategory/{blogId}/{categoryId}")
    public String deleteCategory(@PathVariable int blogId, @PathVariable int categoryId, CategoryVO categoryVO){
        categoryService.deleteCategory(categoryVO);
        return "redirect:/blogAdmin/adminCategory/" + blogId;
    }
}
