package com.vijay.restapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.vijay.restapp.model.Account;
import com.vijay.restapp.service.AccountService;

import java.util.List;
import java.util.Map;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@GetMapping("/accounts")	
	public List<Account> accounts() {
		return accountService.accounts();
	}
	
	@GetMapping("/accounts/{id}")
	public Account getAccount(@PathVariable Long id) {
		return accountService.getAccount(id);
	}
	
	@PostMapping("/accounts")
	public Account addAccount(@RequestBody Account account) {
		return accountService.addAccount(account);
	}
	
	@PutMapping("/accounts")
	public Map<String, String> updateAccount(@RequestBody Account account) {
		return accountService.updateAccount(account);
	}
	
	@DeleteMapping("/accounts/{id}")
	public Map<String, String> deleteAccount(@PathVariable Long id) {
		return accountService.deleteAccount(id);
	}
}
