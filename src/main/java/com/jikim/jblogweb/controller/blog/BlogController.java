package com.jikim.jblogweb.controller.blog;


import com.jikim.jblogweb.biz.blog.BlogService;
import com.jikim.jblogweb.biz.blog.BlogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String index() {
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
