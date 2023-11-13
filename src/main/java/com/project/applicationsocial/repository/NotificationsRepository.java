package com.project.applicationsocial.repository;

import com.project.applicationsocial.model.entity.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NotificationsRepository extends JpaRepository<Notifications, UUID> {
}
