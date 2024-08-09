package com.BlogApp.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApp.Services.NotificationService;
import com.BlogApp.entites.Notification;

@RestController
@RequestMapping("/api")
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService; 
	
	@GetMapping("/notification")
	public List<Notification> notification() {
		return this.notificationService.getAllNotifications();
	}

}
