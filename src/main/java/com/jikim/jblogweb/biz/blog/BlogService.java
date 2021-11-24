package com.jikim.jblogweb.biz.blog;

import com.jikim.jblogweb.biz.user.UserVO;

import javax.servlet.http.HttpServletRequest;

public interface BlogService {
    void insertBlog(BlogVO vo, HttpServletRequest request);

    BlogVO getBlog(UserVO vo);
}
