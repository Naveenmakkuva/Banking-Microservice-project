package com.pracatice.transaction_service.service;

import java.util.List;

import com.pracatice.transaction_service.model.TransactionModel;

public interface TransactionService {

	public List<TransactionModel> getAllTransactionsUsingFeign(Long accountId);

	public TransactionModel addTransaction(TransactionModel transaction);

	public TransactionModel getTransactionById(Long transactionId);

	List<TransactionModel> getAllTransactions();

}
