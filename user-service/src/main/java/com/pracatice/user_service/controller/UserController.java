package com.pracatice.user_service.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.pracatice.user_service.model.AccountModel;
import com.pracatice.user_service.model.UserModel;
import com.pracatice.user_service.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private static final String SERVICE_NAME = "user-service";

	@PostMapping("/save")
	public ResponseEntity<UserModel> addUser(@RequestBody UserModel user) {
		return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<UserModel>> getAllUsers() {
		return new ResponseEntity<List<UserModel>>(userService.getAllUsers(), HttpStatus.FOUND);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserModel> getById(@PathVariable("userId") Long userId) {
		return new ResponseEntity<UserModel>(userService.getUserById(userId), HttpStatus.FOUND);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<String> removeUserById(@PathVariable("userId") Long userId) {
		return new ResponseEntity<String>(userService.deleteUser(userId), HttpStatus.OK);
	}

//	API internal communication to account service to get all account details for the specified userId.
	@CircuitBreaker(name = SERVICE_NAME, fallbackMethod = "userServiceFallBackMethod")  // fault tolerance.
	@GetMapping("/accounts/{userId}")
	public ResponseEntity<List<AccountModel>> getAllAccountsWithUserid(@PathVariable("userId") Long userId) {
		return new ResponseEntity<List<AccountModel>>(userService.getAllAccountsWithUserid(userId), HttpStatus.OK);
	}

	public ResponseEntity<List<AccountModel>> userServiceFallBackMethod(Exception e) {
		List<AccountModel> accounts = new ArrayList<>();
		LOGGER.info("fall back method called from user service since account service is down.");
		return new ResponseEntity<List<AccountModel>>(accounts, HttpStatus.BAD_GATEWAY);
	}

}
