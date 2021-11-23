package com.jikim.jblogweb.biz.blog;

import lombok.Data;

@Data
public class BlogVO {
    private int blogId;
    private String title;
    private String tag;
    private int cntDisplayPost = 0;
    private String status;
    private int userId;
}
