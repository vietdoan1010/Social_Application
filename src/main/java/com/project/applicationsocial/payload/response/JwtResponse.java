package com.project.applicationsocial.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private UUID id;
    private String username;
    private String roles;
    private String first_name;
    private String last_name;

}
