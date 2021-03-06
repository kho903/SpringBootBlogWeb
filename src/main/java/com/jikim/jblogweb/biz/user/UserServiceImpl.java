package com.jikim.jblogweb.biz.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserVO getUser(UserVO vo) {
        return userDAO.getUser(vo);
    }

    @Override
    public List<UserVO> getUserList() {
        return userDAO.getUserList();
    }

    @Override
    public void insertUser(UserVO vo) {
        userDAO.insertUser(vo);
    }
}
