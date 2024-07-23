package com.tekpyramid.supportportal.data.models.entity;

import com.tekpyramid.supportportal.data.models.dto.BaseEntity;
import com.tekpyramid.supportportal.utils.CommonConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@EqualsAndHashCode(callSuper = true)
@Document(value = CommonConstants.TEAMS_COLLECTION)
public class Team extends BaseEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private String description;

}
