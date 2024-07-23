package com.tekpyramid.supportportal.data.models.entity;

import com.tekpyramid.supportportal.data.models.dto.SubTopic;
import com.tekpyramid.supportportal.utils.CommonConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(value = CommonConstants.TOPIC_COLLECTION)
public class Topic {

    @Id
    private String id;
    private String topicName;
    private List<SubTopic> subTopics;
}
