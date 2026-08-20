package com.nt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nt.entity.UserEntity;
import com.nt.repo.IUserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
    private IUserRepo userRepo;
	
	@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		System.out.println("Loading user: " + email);
		UserEntity user = userRepo.findByEmail(email).orElseThrow(() ->
                          new UsernameNotFoundException("User not found"));
		System.out.println("User found: " + user.getEmail());

		return User.builder()
				.username(user.getEmail())
                .password(user.getPassword())
                .build();
	}

}
