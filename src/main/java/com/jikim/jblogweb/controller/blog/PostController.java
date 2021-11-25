package com.jikim.jblogweb.controller.blog;

import com.jikim.jblogweb.biz.post.PostService;
import com.jikim.jblogweb.biz.post.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/blogAdmin/adminPost")
    public String insertPostView() {
        return "adminPost";
    }

    @PostMapping("/blogAdmin/adminPost")
    public String insertPost(PostVO postVO) {
        postService.insertPost(postVO);
        return "redirect:/blogMain/";
    }
}
