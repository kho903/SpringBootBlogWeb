package com.jikim.jblogweb.biz.blog;

import com.jikim.jblogweb.biz.user.UserDAO;
import com.jikim.jblogweb.biz.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDAO blogDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    public void insertBlog(BlogVO vo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        blogDAO.insertBlog(vo, session);
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
