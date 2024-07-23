package com.tekpyramid.supportportal.data.models.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDto {

    private String id;
    private String name;
    private String password;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+[.]+[a-zA-Z0-9.-]{2,4}$"
            , message = "Please provide a valid email address")
    private String email;

    @Max(value= 9999999999L, message = "Phone number must be 10 digits.")
    @Min(value = 6000000000L, message = "Phone Number must be in range for mobile.")

    private long phone;
    private int numberOfTicket;
    private String team;

}