package com.xa.service.impl;

import com.xa.mapper.StudyMapper;
import com.xa.pojo.Blog;
import com.xa.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studyService")
public class StudyServiceImpl implements StudyService {

    @Autowired
    private StudyMapper studyMapper;

    @Override
    public boolean summary(Blog blog) {

        return studyMapper.summary(blog) == 1?true:false;
    }

    @Override
    public List<Blog> listAll() {
        return studyMapper.listAll();
    }
}
