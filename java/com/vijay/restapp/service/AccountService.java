package com.vijay.restapp.service;

import java.util.List;
import java.util.Optional;

import com.vijay.restapp.model.Account;

public interface AccountService {
	
	public Optional<List<Account>> accounts();
	
	public Optional<Account> addAccount(Account account);
	
	public Optional<Account> getAccount(Long id);
	
	public Optional<Account> updateAccount(Long id, Account account);

	public Optional<Account> deleteAccount(Long id);
}
