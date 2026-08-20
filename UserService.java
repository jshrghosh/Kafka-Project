package com.nt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nt.dto.UserCreatedEvent;
import com.nt.entity.UserEntity;
import com.nt.exception.EmailAlreadyExistsException;
import com.nt.producer.UserEventProducer;
import com.nt.repo.IUserRepo;

@Service
public class UserService implements IUserService{
	@Autowired
	private IUserRepo userRepo;
	
	@Autowired
	private UserEventProducer producer;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserEntity saveUser(String firstName,String lastName, String email,String phone,String address,String password) {
		System.out.println("Step 1");
		UserEntity user=new UserEntity();
		System.out.println("Step 2");
		
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhone(phone);
		user.setAddress(address);
		
		System.out.println("Step 3");
		
		user.setPassword(passwordEncoder.encode(password));
		
		System.out.println("Step 4");
		try {
			UserEntity savedUser = userRepo.save(user);
			System.out.println("Step 5");
			UserCreatedEvent event = new UserCreatedEvent();
			event.setId(savedUser.getId());
			event.setFirstName(savedUser.getFirstName());
			event.setLastName(savedUser.getLastName());
			event.setEmail(savedUser.getEmail());
			System.out.println("Publishing Event: "+event);
			
			if (userRepo.existsByEmail(user.getEmail())) {
	            throw new EmailAlreadyExistsException("Email is already registered.");
	        }
			producer.publish(event);
			return savedUser;
		}catch(Exception e) {
			System.out.println("===== SAVE FAILED =====");
		    e.printStackTrace();
		    throw e;
		}
		
		
		
	}
	
	
	@Override
	public boolean deleteUserById(Integer id) {
		if(userRepo.existsById(id)) {
			userRepo.deleteById(id);
			return true;
		}
		return false;
	}
}
