package com.jikim.jblogweb.controller.blog;

import com.jikim.jblogweb.biz.user.UserService;
import com.jikim.jblogweb.biz.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

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
    public String login(UserVO vo, HttpSession session) {
        UserVO user = userService.getUser(vo);
        if (user!= null) {
            // 로그인이 성공한 겨웅 세션에 사용자 정보를 저장한다.
            session.setAttribute("user", user);

            // TODO : 로그인 성공한 사용자가 블로그를 소유한 사용자인지 조회하여 세션에 등록한다.
//            session.setAttribute();
        }
        // 인덱스 페이지로 이동한다.
        return "redirect:/";
    }
}
