package com.pracatice.transaction_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pracatice.transaction_service.config.TransactionFeignConfig;
import com.pracatice.transaction_service.model.AccountModel;
import com.pracatice.transaction_service.model.TransactionModel;
import com.pracatice.transaction_service.repository.TransactionRepository;
import com.pracatice.transaction_service.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private TransactionFeignConfig transactionFeignConfig;

	@Override
	public List<TransactionModel> getAllTransactionsUsingFeign(Long accountId) {
		return transactionRepository.findAll().stream().filter(f -> f.getAccountId().equals(accountId))
				.collect(Collectors.toList());
	}

//	internal call to acc service using feign client.
	@Override
	public TransactionModel addTransaction(TransactionModel transaction) {
		LOGGER.info("transactionServie got inside transactionServiceImpl :: {}",transaction.toString());
		ResponseEntity<AccountModel> account = transactionFeignConfig.updateAccount(transaction);
		LOGGER.info("Account Updated {}", account.toString());
		return transactionRepository.save(transaction);
	}

	@Override
	public TransactionModel getTransactionById(Long transactionId) {
		return transactionRepository.findById(transactionId).get();
	}

	@Override
	public List<TransactionModel> getAllTransactions() {
		return transactionRepository.findAll();
	}

}
