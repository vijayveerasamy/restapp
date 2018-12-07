package com.vijay.restapp.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import com.vijay.restapp.model.Account;
import com.vijay.restapp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);	
	private final Map<Long, Account> accounts = new HashMap<Long, Account>();
    private final AtomicLong counter = new AtomicLong();
    private final Lock lock = new ReentrantLock();

	@Override
	public Optional<List<Account>> accounts() {
		return Optional.ofNullable( accounts.values().stream().collect(Collectors.toList()) );
	}

	@Override
	public Optional<Account> addAccount(Account account) {
		lock.lock();
		account.setId((Long) counter.incrementAndGet());
		accounts.put(account.getId(), account);
		lock.unlock();
		LOGGER.info(account.toString()+" added successfully");
		return Optional.of( account );		
	}
	
	@Override
	public Optional<Account> getAccount(Long id) {
		return Optional.ofNullable( accounts.get(id) );
	}

	@Override
	public Optional<Account> updateAccount(Long id, Account account) {
		lock.lock();
		Account acc = accounts.put(id, account);
		lock.unlock();
		LOGGER.info(acc.toString()+" updated successfully");
		return (acc instanceof Account) ? Optional.of( account ) : Optional.empty();
	}

	@Override
	public Optional<Account> deleteAccount(Long id) {
		lock.lock();
		Account acc = accounts.remove(id);
		lock.unlock();
		LOGGER.info(acc.toString()+" deleted successfully");
		return Optional.ofNullable( acc );
	}

}
