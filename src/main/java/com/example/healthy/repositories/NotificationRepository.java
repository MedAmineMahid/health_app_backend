package com.example.healthy.repositories;

import com.example.healthy.entities.Activity;
import com.example.healthy.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
