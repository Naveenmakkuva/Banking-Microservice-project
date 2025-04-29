package com.pracatice.transaction_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pracatice.transaction_service.model.TransactionModel;
import com.pracatice.transaction_service.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
//	using feign client internal communication b/w microservices
	@GetMapping("/usingFeign/{accountId}")
	public ResponseEntity<List<TransactionModel>> getAllTransactions(@PathVariable("accountId") Long accountId){
		return new ResponseEntity<List<TransactionModel>>(transactionService.getAllTransactionsUsingFeign(accountId),HttpStatus.OK);
	}
	
	@PostMapping("/save")  // this will internally call acc service to update balance / each transaction.
	public ResponseEntity<TransactionModel> addTransaction(@RequestBody TransactionModel transaction){
		return new ResponseEntity<>(transactionService.addTransaction(transaction), HttpStatus.CREATED);
	}
	
	@GetMapping("/transactionId")
	public ResponseEntity<TransactionModel> getTransactionById(@PathVariable("transactionId") Long transactionId){
		return new ResponseEntity<>(transactionService.getTransactionById(transactionId), HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<TransactionModel>> getAllTransactions(){
		return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.FOUND);
	}

}
