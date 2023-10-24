package com.project.applicationsocial.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private String title;

    private String body;

    private String status;

    private Number totalLike;

    private  Number totalComment;

    private UUID createdBy;

    private Timestamp createdAt;
}
