package com.tekpyramid.supportportal.data.models.entity;

import com.tekpyramid.supportportal.utils.CommonConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(value = CommonConstants.FAQ_COLLECTION)
public class Faq {
    private String question;
    private String answer;
}
