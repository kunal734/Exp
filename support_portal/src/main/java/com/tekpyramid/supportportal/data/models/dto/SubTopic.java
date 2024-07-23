package com.tekpyramid.supportportal.data.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class SubTopic {
    private String name;
    private String desc;
    private List<String> steps;
}
