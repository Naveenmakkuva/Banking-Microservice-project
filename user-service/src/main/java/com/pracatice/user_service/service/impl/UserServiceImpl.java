package com.pracatice.user_service.service.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pracatice.user_service.model.AccountModel;
import com.pracatice.user_service.model.UserModel;
import com.pracatice.user_service.repository.UserRepository;
import com.pracatice.user_service.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	private static String DELETE_MESSAGE = "user details removed successfully for Id :: ";
	
	private static final String BASE_URL = "http://localhost:8082/accounts/";
	
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserModel addUser(UserModel user) {
		LOGGER.info("saving user :: {}", user.toString());
		userRepository.save(user);
		return user;
	}

	@Override
	public List<UserModel> getAllUsers() {
		LOGGER.info("Fetching all user data..");
		return userRepository.findAll();
	}

	@Override
	public UserModel getUserById(Long userId) {
		LOGGER.info("Fetching user details with id {}", userId);
		return userRepository.findById(userId).get();
	}

	@Override
	public String deleteUser(Long userId) {
		LOGGER.info("Deleting user details with id {}", userId);
		userRepository.deleteById(userId);
		return DELETE_MESSAGE + userId;
	}

	@Override
	public List<AccountModel> getAllAccountsWithUserid(Long userId) {
		LOGGER.info("calling account service to fetch account details");
		List<AccountModel> accountLis = Arrays.asList(restTemplate.getForObject(BASE_URL + userId, AccountModel[].class));
		return accountLis;
	}

}
