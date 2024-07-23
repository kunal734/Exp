package com.tekpyramid.supportportal.repository;

import com.tekpyramid.supportportal.data.models.entity.Faq;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface FaqRepository extends MongoRepository<Faq, String> {
}
