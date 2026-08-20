package com.nt.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.UserEntity;

public interface IUserRepo extends JpaRepository<UserEntity,Integer>{
	Optional<UserEntity> findByEmail(String email);
	
	boolean existsByEmail(String email);

}
