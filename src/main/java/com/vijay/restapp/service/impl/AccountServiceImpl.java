package com.vijay.restapp.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import java.util.HashMap;

import com.vijay.restapp.model.Account;
import com.vijay.restapp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	private final Map<Long, Account> accounts = new HashMap<Long, Account>();
    private final AtomicLong counter = new AtomicLong();

	@Override
	public List<Account> accounts() {
		return accounts.values().stream().collect(Collectors.toList());
	}

	@Override
	public Account addAccount(Account account) {
		Long id = (Long) counter.incrementAndGet();
		account.setId(id);
		accounts.put(id, account);
		return account;
	}
	
	@Override
	public Account getAccount(Long id) {
		return accounts.get(id);
	}

	@Override
	public Map<String, String> updateAccount(Account account) {
		Account acc = accounts.put(account.getId(), account);
		String message = (acc instanceof Account) ? "account successfully updated" : "account not found";
		Map<String, String> msgMap = new HashMap<String, String>();
		msgMap.put("message", message);
		return msgMap;
	}

	@Override
	public Map<String, String> deleteAccount(Long id) {
		Account acc = accounts.remove(id);
		String message = (acc instanceof Account) ? "account successfully deleted" : "account not found";
		Map<String, String> msgMap = new HashMap<String, String>();
		msgMap.put("message", message);
		return msgMap;
	}

}
