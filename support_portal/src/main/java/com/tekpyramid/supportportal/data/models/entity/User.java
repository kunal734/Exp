package com.tekpyramid.supportportal.data.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tekpyramid.supportportal.data.models.dto.BaseEntity;
import com.tekpyramid.supportportal.data.models.enums.Access;
import com.tekpyramid.supportportal.data.models.enums.Role;
import com.tekpyramid.supportportal.data.models.enums.Status;
import com.tekpyramid.supportportal.utils.CommonConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(value = CommonConstants.USER_COLLECTION)
public class User extends BaseEntity {

    @Id
    private String id;
    private String name;

    @JsonIgnore
    private String password;

    @Indexed(unique = true)
    private String email;
    @Indexed(unique = true)
    private String phone;
    private Role role;
    private Status status;
    private Access access;

    @Transient
    private int numberOfTicket;
    private String team;

    @JsonIgnore
    private String token;

    public void setFields(Role role, Status status, Access access) {
        this.role = role;
        this.status = status;
        this.access = access;
    }
}