package com.pracatice.account_service.service;

import java.util.List;

import com.pracatice.account_service.model.AccountModel;
import com.pracatice.account_service.model.TransactionModel;

public interface AccountService {

	public AccountModel createAccount(AccountModel account);

	public List<AccountModel> getAllAccounts();

	public AccountModel getAccountById(Long accountId);

	public List<AccountModel> getAllAccountsWithUserId(Long userId);

	public Double getBalanceForAcc(Long accId);

	public List<TransactionModel> getAllAccountTransactions(Long id);

	public AccountModel updateAccountAsPerTransaction(TransactionModel transaction);

}
