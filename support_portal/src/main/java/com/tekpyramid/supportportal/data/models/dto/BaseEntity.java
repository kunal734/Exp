package com.tekpyramid.supportportal.data.models.dto;

import com.tekpyramid.supportportal.utils.CommonUtil;
import lombok.Data;

@Data
public class BaseEntity {

    private String createdOn;
    private String createdByName;
    private String createdByEmail;

    private String modifiedOn;
    private String modifiedByName;
    private String modifiedByEmail;

    public void createEntity(String name, String email){
        this.createdByName = name;
        this.createdByEmail = email;
        this.createdOn = CommonUtil.getCurrentDate();
    }

    public void modifyEntity(String name, String email){
        this.modifiedByName = name;
        this.modifiedByEmail = email;
        this.modifiedOn = CommonUtil.getCurrentDate();
    }
}
