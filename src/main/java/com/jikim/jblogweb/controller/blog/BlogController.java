package com.jikim.jblogweb.controller.blog;


import com.jikim.jblogweb.biz.blog.BlogService;
import com.jikim.jblogweb.biz.blog.BlogVO;
import com.jikim.jblogweb.biz.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/")
    public String index(HttpSession session, Model model) {
        UserVO user = (UserVO) session.getAttribute("user");
        if (user != null) {
            BlogVO blog = blogService.getBlog(user);
            if (blog != null)
                model.addAttribute("blog", blog);
        }
        return "forward:index.jsp";
    }

    @GetMapping("/blogcreate")
    public String blogcreateView() {
        return "blogcreate";
    }

    @PostMapping("/blogcreate")
    public String blogcreate(BlogVO vo, HttpServletRequest request) {
        blogService.insertBlog(vo, request);
        return "redirect:/";
    }

}
