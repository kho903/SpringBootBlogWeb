package com.jikim.jblogweb.biz.post;

public interface PostService {
    void insertPost(PostVO vo);

    PostVO getPost(PostVO vo);

    void updatePost(PostVO vo);

    void deletePost(PostVO vo);
}
