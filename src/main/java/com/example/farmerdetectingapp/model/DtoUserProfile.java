package com.example.farmerdetectingapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoUserProfile {

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;
}
