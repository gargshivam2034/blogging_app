package com.BlogApp.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BlogApp.entites.Notification;

public interface NotificationRepo extends JpaRepository<Notification, Integer> {

}
