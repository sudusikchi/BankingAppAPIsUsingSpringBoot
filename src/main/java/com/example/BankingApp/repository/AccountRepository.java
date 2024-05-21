package com.example.BankingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BankingApp.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	
}
