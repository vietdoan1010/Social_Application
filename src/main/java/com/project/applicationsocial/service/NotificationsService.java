package com.project.applicationsocial.service;

import java.sql.Timestamp;
import java.util.UUID;

public interface NotificationsService {
    void createNotifi(UUID userID, UUID toUser, String content, Timestamp createdAt);

    void deleteNotifi(UUID userID, UUID notifiID);
}
