package com.codingdojo.BeltReviewer.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.codingdojo.BeltReviewer.models.Message;

public interface MessageRepo extends CrudRepository<Message, Long>{
	List<Message> findAll();
	
}
