package com.example.BankingApp.mapper;

import java.util.ArrayList;
import java.util.List;

import com.example.BankingApp.dto.AccountDto;
import com.example.BankingApp.entity.Account;

public class AccountMapper {

	public static Account mapToAccount(AccountDto accountDto) {
		Account account = new Account(accountDto.getId(), accountDto.getAccountHolderName(), accountDto.getBalance());

		return account;
	}

	public static AccountDto mapToAccountDto(Account account) {
		AccountDto accountDto = new AccountDto(account.getId(), account.getAccountHolderName(), account.getBalance());

		return accountDto;
	}

//	//for list of accounts
//	public static List<AccountDto> mapToAccountDtoList(List<Account> accountList) {
//		List<AccountDto> accountDtoList = new ArrayList<>();
//
//		for (Account account : accountList) {
//			accountDtoList.add(mapToAccountDto(account));
//		}
//
//		return accountDtoList;
//	}
}
