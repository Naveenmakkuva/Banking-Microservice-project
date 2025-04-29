package com.pracatice.transaction_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pracatice.transaction_service.model.TransactionModel;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, Long>{

}
