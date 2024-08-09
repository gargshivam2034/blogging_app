package com.BlogApp.Observable;

import com.BlogApp.Observer.Observer;

public interface NotifyMeOnComment {
	
	void add(Observer observer);
	void remove(Observer observer);
	void notifyMe(String name);
	
}
