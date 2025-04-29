package com.pracatice.account_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pracatice.account_service.model.AccountModel;
import com.pracatice.account_service.model.TransactionModel;
import com.pracatice.account_service.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/save")
	public ResponseEntity<AccountModel> addAccount(@RequestBody AccountModel account){
		return new ResponseEntity<AccountModel>(accountService.createAccount(account), HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<AccountModel>> getAllAccounts(){
		return new ResponseEntity<List<AccountModel>>(accountService.getAllAccounts(),HttpStatus.FOUND);
	}
	
	@GetMapping("/all/{accountId}")
	public ResponseEntity<AccountModel> getAccountsById(@PathVariable("accountId") Long accountId){
		return new ResponseEntity<AccountModel>(accountService.getAccountById(accountId),HttpStatus.FOUND);
	}
	
	@GetMapping("/balance/{accId}")
	public ResponseEntity<Double> getBalanceForAccountId(@PathVariable("accId") Long accId){
		return new ResponseEntity<Double>(accountService.getBalanceForAcc(accId),HttpStatus.OK);
		
	}
	
//	internal communication API using REST.
	@GetMapping("/{userId}")
	public ResponseEntity<List<AccountModel>> getAllAccountsWithUserId(@PathVariable("userId") Long userId){
		return new ResponseEntity<List<AccountModel>>(accountService.getAllAccountsWithUserId(userId),HttpStatus.FOUND);
	}
	
//	internal communication using Feign Client.
//	API internal communication to account service to get all transaction details for the specified userId trough an account.
	@GetMapping("acc/accountId")
	public ResponseEntity<List<TransactionModel>> getAllAccountTransactions(@PathVariable("accountId") Long id){
		return new ResponseEntity<List<TransactionModel>>(accountService.getAllAccountTransactions(id),HttpStatus.OK);
	}
	
//	feign client from transaction service.
	@PutMapping("/update")
	public ResponseEntity<AccountModel> updateAccount(@RequestBody TransactionModel transaction){
		return new ResponseEntity<AccountModel>(accountService.updateAccountAsPerTransaction(transaction), HttpStatus.OK);
	}
}
