package com.jikim.jblogweb.biz.user;

import lombok.Data;

// BLOG_USER와 매핑되는 클래스
@Data
public class UserVO {
    private int userId;         // 회원 일련번호
    private String id;          // 회원 아이디
    private String password;    // 비밀번호
    private String userName;    // 이름
    private String role;        // 권한
}
