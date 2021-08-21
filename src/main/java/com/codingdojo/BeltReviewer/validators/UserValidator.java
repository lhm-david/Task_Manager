package com.codingdojo.BeltReviewer.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codingdojo.BeltReviewer.models.User;
import com.codingdojo.BeltReviewer.repositories.UserRepo;

@Component
public class UserValidator implements Validator {
	@Autowired
	private UserRepo uRepo;
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		User newuser = (User) target;
		if(!newuser.getPassword().equals(newuser.getPasswordConfirmation())) {
			errors.rejectValue("passwordConfirmation", "Match","Password Not Match.");
		}
		if(this.uRepo.existsByEmail(newuser.getEmail())) {
			errors.rejectValue("Email", "Unique","This Email was already registered.");
		}
	}
	

}
