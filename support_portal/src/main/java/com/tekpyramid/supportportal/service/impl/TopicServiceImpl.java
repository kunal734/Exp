package com.tekpyramid.supportportal.service.impl;

import com.tekpyramid.supportportal.dao.TopicDao;
import com.tekpyramid.supportportal.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicDao topicDao;
}
