package com.jikim.jblogweb.biz.user;

public interface UserService {
    UserVO getUser(UserVO vo);

    void insertUser(UserVO vo);
}
