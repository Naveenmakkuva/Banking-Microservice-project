package com.pracatice.account_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pracatice.account_service.config.AccountFeignClient;
import com.pracatice.account_service.model.AccountModel;
import com.pracatice.account_service.model.TransactionModel;
import com.pracatice.account_service.repository.AccountRepository;
import com.pracatice.account_service.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

	private static final String CREDIT = "credit";

	@Autowired
	private AccountRepository accountrepository;

//	feign cleint config interface
	@Autowired
	private AccountFeignClient accountFeignClient;

	@Override
	public AccountModel createAccount(AccountModel account) {
		LOGGER.info("Account added with details :: {}", account.toString());
		accountrepository.save(account);
		return account;
	}

	@Override
	public List<AccountModel> getAllAccounts() {
		LOGGER.info("Found all account details");
		return accountrepository.findAll();
	}

	@Override
	public AccountModel getAccountById(Long accountId) {
		LOGGER.info("Fetching account by ID {}", accountId);
		return accountrepository.findById(accountId).get();
	}

	@Override
	public Double getBalanceForAcc(Long accId) {
		return accountrepository.findById(accId).get().getAccountBalance();
	}

	@Override
	public List<AccountModel> getAllAccountsWithUserId(Long userId) {
		return accountrepository.findAll().stream().filter(f -> f.getUserId().equals(userId))
				.collect(Collectors.toList());
	}

//	Using feign client
	@Override
	public List<TransactionModel> getAllAccountTransactions(Long id) {
		ResponseEntity<List<TransactionModel>> transactions = accountFeignClient.getAllTransactions(id);
		return transactions.getBody();
	}

//	using feign client api call.
	@Override
	public AccountModel updateAccountAsPerTransaction(TransactionModel transaction) {
		LOGGER.info("transaction details received {}",transaction.toString());
		AccountModel account = accountrepository.findById(transaction.getAccountId()).get();
		if (transaction.getTransactionType().equalsIgnoreCase(CREDIT)) {
			account.setAccountBalance(account.getAccountBalance() + transaction.getTransactionAmount());
			LOGGER.info("updated account balance with credit {}", account.getAccountBalance());
			accountrepository.save(account);
		} else {
			account.setAccountBalance(account.getAccountBalance() - transaction.getTransactionAmount());
			LOGGER.info("updated account balance with credit {}", account.getAccountBalance());
			accountrepository.save(account);
		}
		return account;
	}

}
