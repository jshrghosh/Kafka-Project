package com.nt.service;

import com.nt.entity.UserEntity;

public interface IUserService {
	public UserEntity saveUser(String firstName,String lastName,String email,String phone,String address,String password);
	public boolean deleteUserById(Integer id);
}
