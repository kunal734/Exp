package com.tekpyramid.supportportal.data.models.entity;

import com.tekpyramid.supportportal.data.models.dto.BaseEntity;
import com.tekpyramid.supportportal.data.models.dto.Comment;
import com.tekpyramid.supportportal.data.models.enums.TicketStatus;
import com.tekpyramid.supportportal.utils.CommonConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(value = CommonConstants.TICKET_COLLECTION)
public class Ticket extends BaseEntity {

    @Id
    private String id;
    private String summary;
    private TicketStatus status;
    private String description;
    private String severity;
    private String priority;
    private String license;
    private String issueRelated;
    private String assignedToName;
    private String assignedToEmail;
    private String assignedToTeam;
    private List<String> file;

    private List<Comment> comments;
}
