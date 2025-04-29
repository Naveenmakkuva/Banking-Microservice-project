package com.pracatice.user_service.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class AccountModel {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long accountId;
	private Long userId;
	private Long accountNo;
	private String accountType;
	private String accountStatus;
	private Double accountBalance;
	@OneToMany
	private List<TransactionModel> accountTransactions;
}
