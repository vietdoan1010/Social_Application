package com.project.applicationsocial.payload.request;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class FollowRequest {
    private List<UUID> id;
}
