package com.jikim.jblogweb.biz.user;

import java.util.List;

public interface UserService {
    UserVO getUser(UserVO vo);

    List<UserVO> getUserList();

    void insertUser(UserVO vo);
}
