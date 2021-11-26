package com.jikim.jblogweb.controller.blog;

import com.jikim.jblogweb.biz.blog.BlogService;
import com.jikim.jblogweb.biz.blog.BlogVO;
import com.jikim.jblogweb.biz.category.CategoryService;
import com.jikim.jblogweb.biz.post.PostService;
import com.jikim.jblogweb.biz.post.PostVO;
import com.jikim.jblogweb.biz.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/blogAdmin/adminPost")
    public String insertPostView(Model model, HttpSession session) {
        UserVO user = (UserVO) session.getAttribute("user");
        BlogVO blog = blogService.getBlog(user);
        model.addAttribute("categoryList", categoryService.getCategoryList(blog));
        return "adminPost";
    }

    @PostMapping("/blogAdmin/adminPost")
    public String insertPost(PostVO postVO, HttpSession session) {
        UserVO user = (UserVO) session.getAttribute("user");
        int blogId = blogService.getBlog(user).getBlogId();
        postService.insertPost(postVO);
        return "redirect:/blogMain/" + blogId;
    }

    @GetMapping("/blogAdmin/updatePost/{postId}")
    public String updatePostView(@PathVariable String postId, Model model, HttpSession session) {
        UserVO user = (UserVO) session.getAttribute("user");
        BlogVO blog = blogService.getBlog(user);
        model.addAttribute("categoryList", categoryService.getCategoryList(blog));
        model.addAttribute("editPostId", postId);
        return "adminPost";
    }

    @PostMapping("/blogAdmin/updatePost/{postId}")
    public String updatePost(@PathVariable int postId, PostVO postVO, HttpSession session) {
        UserVO user = (UserVO) session.getAttribute("user");
        String blogId = String.valueOf(blogService.getBlog(user).getBlogId());
        postService.updatePost(postVO);
        return "redirect:/blogMain/" + blogId;
    }

    @RequestMapping("/blogAdmin/deletePost/{postId}")
    public String deletePost(@PathVariable int postId, PostVO postVO, HttpSession session) {
        UserVO user = (UserVO) session.getAttribute("user");
        String blogId = String.valueOf(blogService.getBlog(user).getBlogId());
        postService.deletePost(postVO);
        return "redirect:/blogMain/" + blogId;
    }
}
