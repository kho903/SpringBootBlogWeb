package com.jikim.jblogweb.biz.category;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryVO {
    private int blogId;
    private int categoryId;
    private String categoryName;
    private String displayType;
    private int cntDisplayPost;
    private String description;
    private Date createdDate;
    private Date modifiedDate;
}
