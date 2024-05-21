package com.example.BankingApp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.BankingApp.dto.AccountDto;
import com.example.BankingApp.entity.Account;
import com.example.BankingApp.mapper.AccountMapper;
import com.example.BankingApp.repository.AccountRepository;
import com.example.BankingApp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository accRepo;

	public AccountServiceImpl(AccountRepository accRepo) {
		this.accRepo = accRepo;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accRepo.save(account);

		return AccountMapper.mapToAccountDto(savedAccount);
	}

//	@Override
//	public List<AccountDto> getAllAccounts() {
//		return AccountMapper.mapToAccountDtoList(accRepo.findAll());
//	}

	// OOORRRR
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accRepo.findAll();

		return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
	}

	
	@Override
	public AccountDto getAccountById(Long id) {

		Account account = accRepo.findById(id).orElseThrow(() -> new RuntimeException("Account doesn't exist..."));
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account = accRepo.findById(id).orElseThrow(() -> new RuntimeException("Account doesn't exist..."));

		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account savedAccount = accRepo.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {

		Account account = accRepo.findById(id).orElseThrow(() -> new RuntimeException("Account doesn't exist..."));

		if (account.getBalance() < amount) {
			throw new RuntimeException("Insufficient Amount...");
		}
		double remainBalance = account.getBalance() - amount;
		account.setBalance(remainBalance);
		Account savedAccount = accRepo.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public void deleteAccount(Long id) {
		Account account = accRepo.findById(id).orElseThrow(() -> new RuntimeException("Account doesn't exist..."));
		accRepo.deleteById(id);		
	}


    // Edit holder name using patchMapping
	@Override
	public AccountDto editAccountHolderName(Long id, String newHolderName) {
	    Account account = accRepo.findById(id)
	                              .orElseThrow(() -> new RuntimeException("Account doesn't exist..."));
	    account.setAccountHolderName(newHolderName);
	    Account savedAccount = accRepo.save(account);
	    return AccountMapper.mapToAccountDto(savedAccount);
	}

}
