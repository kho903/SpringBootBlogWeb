package com.jikim.jblogweb.biz.post;

import com.jikim.jblogweb.biz.blog.BlogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDAO postDAO;

    @Override
    public void insertPost(PostVO vo) {
        postDAO.insertPost(vo);
    }

    @Override
    public PostVO getPost(PostVO vo) {
        return postDAO.getPost(vo);
    }

    @Override
    public void updatePost(PostVO vo) {
        postDAO.updatePost(vo);
    }

    @Override
    public void deletePost(PostVO vo) {
        postDAO.deletePost(vo);
    }

    @Override
    public List<PostVO> getPostList(BlogVO blogVO) {
        return postDAO.getPostList(blogVO);
    }

}
