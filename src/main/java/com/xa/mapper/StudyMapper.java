package com.xa.mapper;

import com.xa.pojo.Blog;

import java.util.List;

public interface StudyMapper {
    int summary(Blog blog);

    List<Blog> listAll();

}
