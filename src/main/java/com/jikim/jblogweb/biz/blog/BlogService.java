package com.jikim.jblogweb.biz.blog;

import com.jikim.jblogweb.biz.user.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BlogService {
    void insertBlog(BlogVO vo, HttpServletRequest request);

    BlogVO getBlog(UserVO vo);

    List<BlogVO> getBlogList(BlogVO vo);
}
