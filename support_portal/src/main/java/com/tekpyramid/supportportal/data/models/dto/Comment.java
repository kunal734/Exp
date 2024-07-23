package com.tekpyramid.supportportal.data.models.dto;

import lombok.Data;

@Data
public class Comment {

    private String commentId;
    private String message;
    private boolean isPrivate;

}
