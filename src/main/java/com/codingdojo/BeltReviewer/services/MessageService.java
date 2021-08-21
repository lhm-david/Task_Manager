package com.codingdojo.BeltReviewer.services;

import java.util.List;

import org.springframework.stereotype.Service;


import com.codingdojo.BeltReviewer.models.Message;
import com.codingdojo.BeltReviewer.repositories.MessageRepo;

@Service
public class MessageService {
	private final MessageRepo messageRepo;
	
	public MessageService (MessageRepo messageRepo) {
		this.messageRepo = messageRepo;
	}
	
	public List<Message> getAllMessage(){
		return this.messageRepo.findAll();
	}
	
	public Message createM(Message message) {
		return this.messageRepo.save(message);
	}
}
