package com.pracatice.user_service.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class TransactionModel {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long transactionId;
	private String transactionNumber;
	private LocalDateTime transactionTime;
	private String transactionType;
	private Double transactionAmount;
	private Long accountId;
}
