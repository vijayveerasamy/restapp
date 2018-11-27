package com.vijay.restapp.service;

import java.util.List;
import java.util.Map;

import com.vijay.restapp.model.Account;

public interface AccountService {
	
	public List<Account> accounts();
	
	public Account addAccount(Account account);
	
	public Account getAccount(Long id);
	
	public Map<String, String> updateAccount(Account account);

	public Map<String, String> deleteAccount(Long id);
}
