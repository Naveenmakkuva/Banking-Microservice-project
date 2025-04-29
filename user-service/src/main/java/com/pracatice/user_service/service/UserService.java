package com.pracatice.user_service.service;

import java.util.List;

import com.pracatice.user_service.model.AccountModel;
import com.pracatice.user_service.model.UserModel;

public interface UserService {

	public UserModel addUser(UserModel user);

	public List<UserModel> getAllUsers();

	public UserModel getUserById(Long userId);

	public String deleteUser(Long userId);

	public List<AccountModel> getAllAccountsWithUserid(Long userId);

}
