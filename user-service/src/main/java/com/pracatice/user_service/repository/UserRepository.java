package com.pracatice.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pracatice.user_service.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

}
