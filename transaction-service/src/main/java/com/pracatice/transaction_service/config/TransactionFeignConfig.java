package com.pracatice.transaction_service.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pracatice.transaction_service.model.AccountModel;
import com.pracatice.transaction_service.model.TransactionModel;

@FeignClient(name = "account-service", url = "http://localhost:8082",path = "/accounts")
public interface TransactionFeignConfig {
	
	@PutMapping("/update")
	public ResponseEntity<AccountModel> updateAccount(@RequestBody TransactionModel transaction);
}
