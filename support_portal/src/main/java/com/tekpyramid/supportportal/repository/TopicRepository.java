package com.tekpyramid.supportportal.repository;

import com.tekpyramid.supportportal.data.models.entity.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TopicRepository extends MongoRepository<Topic, String> {
}
