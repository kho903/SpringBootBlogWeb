package com.jikim.jblogweb.biz.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDAO blogDAO;

    public void insertBlog(BlogVO vo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        blogDAO.insertBlog(vo, session);
    }
}
