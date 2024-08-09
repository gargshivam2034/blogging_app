package com.BlogApp.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BlogApp.Respository.NotificationRepo;
import com.BlogApp.Services.NotificationService;
import com.BlogApp.entites.Notification;

@Service
public class NotificationServiceImpl implements NotificationService {

	
	@Autowired
	private NotificationRepo notificationRepo;
	@Override
	public List<Notification> getAllNotifications() {
		List<Notification> listOfNotification=this.notificationRepo.findAll();
		return listOfNotification;
	}

	

}
