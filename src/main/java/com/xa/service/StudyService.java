package com.xa.service;

import com.xa.pojo.Blog;

import java.util.List;

public interface StudyService {
    boolean summary(Blog blog);

    List<Blog> listAll();

}
