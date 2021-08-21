package com.codingdojo.BeltReviewer.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.codingdojo.BeltReviewer.models.User;
import com.codingdojo.BeltReviewer.repositories.UserRepo;

@Service
public class UserService {
	private final UserRepo userRepo;
	
	public UserService (UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	public List<User> getAllUsers(){
		return this.userRepo.findAll();
	}
	
	public User getOneUser(Long id) {
		return this.userRepo.findById(id).orElse(null);
	}
	
	public User register(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return this.userRepo.save(user);
	}
	
	public boolean authenicateUser(String email, String password) {
		User user = this.userRepo.findByEmail(email);
		if (user == null) {
			return false;
		}
		return BCrypt.checkpw(password, user.getPassword());
	}
	
	public User getByEmail(String email) {
		return this.userRepo.findByEmail(email);
	}

}
