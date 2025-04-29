package com.pracatice.account_service.config;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pracatice.account_service.model.TransactionModel;

@FeignClient(name = "transaction-service",url = "http://localhost:8083/",path = "/transactions")
public interface AccountFeignClient {
	
	@GetMapping("/usingFeign/{accountId}")
	public ResponseEntity<List<TransactionModel>> getAllTransactions(@PathVariable("accountId") Long accountId);
}
