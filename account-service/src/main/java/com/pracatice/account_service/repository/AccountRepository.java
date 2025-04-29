package com.pracatice.account_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pracatice.account_service.model.AccountModel;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, Long> {

}
