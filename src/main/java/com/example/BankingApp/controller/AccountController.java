package com.example.BankingApp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BankingApp.dto.AccountDto;
import com.example.BankingApp.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	private AccountService service;

	public AccountController(AccountService service) {
		this.service = service;
	}

	@PostMapping("/save")
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
		AccountDto accountdto = service.createAccount(accountDto);
		return new ResponseEntity<>(accountdto, HttpStatus.OK);
	}

	@GetMapping("/all")
	public List<AccountDto> getAllAccounts() {
		return service.getAllAccounts();
	}

	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") Long id) {

		AccountDto accountDto = service.getAccountById(id);
		return ResponseEntity.ok(accountDto);
	}

	// deposit rest api
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> depositAmount(@PathVariable Long id, @RequestBody Map<String, Double> request) {
		Double amount = request.get("amount");
		AccountDto accountdto = service.deposit(id, amount);
		return ResponseEntity.ok(accountdto);
	}

	// withdraw rest api
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdrawAmount(@PathVariable Long id, @RequestBody Map<String, Double> request) {
		Double amount = request.get("amount");
		AccountDto accountdto = service.withdraw(id, amount);
		return ResponseEntity.ok(accountdto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
		service.deleteAccount(id);
		return ResponseEntity.ok("Account Deleted Successfully....");
	}

	// PatchMapping for account holder
	@PatchMapping("/{id}/editname")
	public ResponseEntity<AccountDto> editAccountHolderName(@PathVariable("id") Long id,
			@RequestBody Map<String, String> request) {
		
		String newHolderName = request.get("holderName");
		AccountDto accountDto = service.editAccountHolderName(id, newHolderName);
		return ResponseEntity.ok(accountDto);
	}

}
