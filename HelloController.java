package com.nt.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.UserEntity;
import com.nt.repo.IUserRepo;
import com.nt.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class HelloController {
	@Autowired
	private IUserRepo userRepo;
	
	@Autowired
	private UserService service;
	
	@PostMapping("/create")
	public ResponseEntity<UserEntity> createUser(@Valid @RequestBody UserEntity user) {
		System.out.println("===== CONTROLLER REACHED =====");
		System.out.println("Inside createUser()");
		UserEntity savedUser = service.saveUser(
				user.getFirstName(),
				user.getLastName(),
				user.getEmail(),
				user.getPhone(),
				user.getAddress(),
				user.getPassword()
				);
		System.out.println("Returning response");
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") Integer id) {
		Optional<UserEntity> user = userRepo.findById(id);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get()); // 200 OK + JSON
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("User not found with id: " + id);
        }
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id){
		boolean deleted=service.deleteUserById(id);
		
		if(deleted) {
			return ResponseEntity.ok("User deleted successfully");
		}else {
			return ResponseEntity.status(404).body("User not found");
		}
	}
}
