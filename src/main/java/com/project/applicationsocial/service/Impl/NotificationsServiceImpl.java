package com.project.applicationsocial.service.Impl;

import com.project.applicationsocial.exception.ForbiddenException;
import com.project.applicationsocial.exception.NotFoundException;
import com.project.applicationsocial.model.entity.Notifications;
import com.project.applicationsocial.repository.NotificationsRepository;
import com.project.applicationsocial.service.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotificationsServiceImpl implements NotificationsService {
    @Autowired
    NotificationsRepository notificationsRep;

    @Override
    public void createNotifi(UUID userID, UUID toUser,String content, Timestamp createdAt) {
        Notifications notification = new Notifications(userID, toUser, content, createdAt);
        notificationsRep.save(notification);
    }

    @Override
    public void deleteNotifi(UUID userID, UUID notifiID) {
        Optional<Notifications> notification = notificationsRep.findById(notifiID);
        if (notification.isEmpty()) {
            throw new NotFoundException("Notification is not found!");
        }
        if (!(notification.get().getToUser().equals(userID)) ) {
            throw new ForbiddenException("Not authorize");
        }
        notificationsRep.delete(notification.get());
    }
}
