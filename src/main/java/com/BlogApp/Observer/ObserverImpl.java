package com.BlogApp.Observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.BlogApp.Respository.NotificationRepo;
import com.BlogApp.entites.Notification;

@Component
public class ObserverImpl implements Observer {

	
	@Autowired
	private NotificationRepo notificationRepo;
	
	@Override
	public void update(String name) {
		Notification notification=new Notification();
		notification.setMessage("New Comment on ${name} Post");
		this.notificationRepo.save(notification);
	}
	

}
