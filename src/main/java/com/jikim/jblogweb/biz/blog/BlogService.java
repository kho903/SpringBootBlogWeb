package com.jikim.jblogweb.biz.blog;

import com.jikim.jblogweb.biz.user.UserVO;

import java.util.List;

public interface BlogService {
    void insertBlog(BlogVO blogVO, UserVO userVO);

    BlogVO getBlog(UserVO vo);

    List<BlogVO> getBlogList(BlogVO vo);
}
