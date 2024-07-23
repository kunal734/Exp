package com.tekpyramid.supportportal.data.models.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TeamDto {

    private String id;

    @NotNull
    private String name;
    private String description;
}
