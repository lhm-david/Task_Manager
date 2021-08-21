package com.codingdojo.BeltReviewer.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.BeltReviewer.models.Event;

@Repository
public interface EventRepo extends CrudRepository <Event, Long>{
	List<Event> findAll();
	List<Event> findByStateContaining(String state);
	List<Event> findByStateNotContaining(String state);
	

}
