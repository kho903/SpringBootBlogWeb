package com.jikim.jblogweb.biz.post;

import com.jikim.jblogweb.biz.blog.BlogVO;

import java.util.List;

public interface PostService {
    void insertPost(PostVO vo);

    PostVO getPost(PostVO vo);

    void updatePost(PostVO vo);

    void deletePost(PostVO vo);

    List<PostVO> getPostList(BlogVO blogVO);
}
