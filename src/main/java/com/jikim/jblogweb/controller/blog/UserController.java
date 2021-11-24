package com.jikim.jblogweb.controller.blog;

import com.jikim.jblogweb.biz.blog.BlogService;
import com.jikim.jblogweb.biz.blog.BlogVO;
import com.jikim.jblogweb.biz.user.UserService;
import com.jikim.jblogweb.biz.user.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/login")
    public String loginView() {
        return "bloglogin";
    }

    @RequestMapping("/insertUser.do")
    public String insertUser(UserVO vo) {
        userService.insertUser(vo);
        return "bloglogin";
    }

    @PostMapping("/login")
    public String login(UserVO vo, HttpSession session, Model model) {
        UserVO user = userService.getUser(vo);
        BlogVO blog = blogService.getBlog(vo);

        if (user!= null) {
            // 로그인이 성공한 겨웅 세션에 사용자 정보를 저장한다.
            session.setAttribute("user", user);

            if (blog != null) {
                model.addAttribute("blog",blog);
            }
        }
        // 인덱스 페이지로 이동한다.
        return "redirect:/";
    }
}
