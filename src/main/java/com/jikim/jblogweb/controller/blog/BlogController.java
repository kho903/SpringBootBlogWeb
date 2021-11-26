package com.jikim.jblogweb.controller.blog;


import com.jikim.jblogweb.biz.blog.BlogService;
import com.jikim.jblogweb.biz.blog.BlogVO;
import com.jikim.jblogweb.biz.category.CategoryService;
import com.jikim.jblogweb.biz.category.CategoryVO;
import com.jikim.jblogweb.biz.post.PostService;
import com.jikim.jblogweb.biz.post.PostVO;
import com.jikim.jblogweb.biz.user.UserService;
import com.jikim.jblogweb.biz.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private PostService postService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(HttpSession session, Model model) {
        UserVO user = (UserVO) session.getAttribute("user");
        if (user != null) {
            BlogVO blog = blogService.getBlog(user);
            if (blog != null)
                session.setAttribute("blog", blog);
        }
        return "forward:index.jsp";
    }

    @GetMapping("/blogcreate")
    public String blogcreateView() {
        return "blogcreate";
    }

    @PostMapping("/blogCreate")
    public String blogcreate(BlogVO blogVo, HttpSession session) {
        UserVO user = (UserVO) session.getAttribute("user");
        blogService.insertBlog(blogVo, user);
        return "redirect:/";
    }

    @RequestMapping("/blogMain/{blogId}")
    public String blogMain(@PathVariable int blogId, Model model) {
        UserVO user = new UserVO();
        user.setUserId(blogId);
        BlogVO blog = blogService.getBlog(user);
        List<PostVO> postList = postService.getPostList(blog);
        postList.sort(new Comparator<PostVO>() {
            @Override
            public int compare(PostVO o1, PostVO o2) {
                if (o1.getPostId() > o2.getPostId())
                    return -1;
                else
                    return 1;
            }
        });
        List<CategoryVO> categoryList = categoryService.getCategoryList(blog);
        model.addAttribute("blog", blog);
        model.addAttribute("postList", postList);
        model.addAttribute("categoryList", categoryList);
        return "blogMain";
    }

    @RequestMapping("/search")
    public String search(BlogVO blogVO, Model model) {
        List<BlogVO> searchResult = blogService.getBlogList(blogVO);
        List<UserVO> userList = userService.getUserList();
        model.addAttribute("searchResult", searchResult);
        model.addAttribute("userList", userList);
        return "forward:index.jsp";
    }

    @GetMapping("/blogAdmin/{blogId}")
    public String blogAdminView(@PathVariable int blogId, Model model) {
        UserVO user = new UserVO();
        user.setUserId(blogId);
        BlogVO blog = blogService.getBlog(user);
        model.addAttribute("blog", blog);
        return "blogadmin_basic";
    }

    @PostMapping("/blogAdmin/{blogId}")
    public String blogAdmin(@PathVariable int blogId, BlogVO blogVO, HttpSession session) {
        blogVO.setBlogId(blogId);
        UserVO user = (UserVO) session.getAttribute("user");
        blogService.updateBlog(blogVO, user);
        return "redirect:/blogMain/" + blogId;
    }

}
