package com.codingdojo.BeltReviewer.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codingdojo.BeltReviewer.models.Event;
import com.codingdojo.BeltReviewer.models.User;
import com.codingdojo.BeltReviewer.repositories.EventRepo;

@Service
public class EventService {
	private final EventRepo eventRepo;
	
	public EventService (EventRepo eventRepo) {
		this.eventRepo = eventRepo;
	}
	
	public List<Event> getAllEvents(){
		return this.eventRepo.findAll();
	}
	
	public List<Event> stateEvents(String state){
		return this.eventRepo.findByStateContaining(state);
	}
	
	public List<Event> otherEvents(String state){
		return this.eventRepo.findByStateNotContaining(state);
	}
	
	public Event getOneEvent(Long id) {
		return this.eventRepo.findById(id).orElse(null);
	}
	
	public Event createEvent(Event event) {
		return this.eventRepo.save(event);
	}
	
	public Event updateEvent(Event event) {
		return this.eventRepo.save(event);
	}
	
	public void joinEvent(Event event, User user) {
		List<User> userWhoJoin = event.getEventUsers();
		userWhoJoin.add(user);
		this.eventRepo.save(event);
	}
	
	public void canceljoinEvent(Event event, User user) {
		List<User> userWhoJoin = event.getEventUsers();
		userWhoJoin.remove(user);
		this.eventRepo.save(event);
	}

	public void deleteEvent(Long id) {
		this.eventRepo.deleteById(id);
	}
}