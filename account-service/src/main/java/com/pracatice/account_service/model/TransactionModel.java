package com.pracatice.account_service.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
public class TransactionModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	private String transactionNumber;
	private LocalDateTime transactionTime;
	private String transactionType;
	private Double transactionAmount;
	private Long accountId;
}
