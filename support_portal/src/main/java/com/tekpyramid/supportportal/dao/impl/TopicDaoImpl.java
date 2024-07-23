package com.tekpyramid.supportportal.dao.impl;

import com.tekpyramid.supportportal.dao.TopicDao;
import com.tekpyramid.supportportal.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TopicDaoImpl implements TopicDao {

    private final TopicRepository topicRepository;
}
