package com.jikim.jblogweb.biz.blog;

import com.jikim.jblogweb.biz.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDAO blogDAO;

    @Override
    public void insertBlog(BlogVO blogVO, UserVO userVO) {
        blogDAO.insertBlog(blogVO, userVO);
    }

    @Override
    public BlogVO getBlog(UserVO vo) {
        return blogDAO.getBlog(vo);
    }

    @Override
    public List<BlogVO> getBlogList(BlogVO vo) {
        return blogDAO.getBlogList(vo);
    }

}
