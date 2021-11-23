package com.jikim.jblogweb.biz.blog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface BlogService {
    void insertBlog(BlogVO vo, HttpServletRequest request);
}
