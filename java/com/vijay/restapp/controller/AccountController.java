package com.vijay.restapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vijay.restapp.model.Account;
import com.vijay.restapp.service.AccountService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

import java.util.List;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
    @RequestMapping(value = "/", method = GET, produces = APPLICATION_JSON_VALUE)	
	public ResponseEntity<List<Account>> accounts() {
        return accountService.accounts()
                .map(list -> new ResponseEntity<>(list, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        return accountService.getAccount(id)
                .map(acc -> new ResponseEntity<>(acc, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
    @RequestMapping(value = "/", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> addAccount(@RequestBody Account account) {
        return accountService.addAccount(account)
                .map(acc -> new ResponseEntity<>(acc, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY));
	}
	
	@RequestMapping(value = "/{id}", method = PUT, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> updateAccount(@PathVariable("id") Long id, @RequestBody Account account) {
        return accountService.updateAccount(id, account)
                .map(acc -> new ResponseEntity<>(acc, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@RequestMapping(value = "/{id}", method = DELETE)
	public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        return accountService.deleteAccount(id)
                .map(acc -> new ResponseEntity<>("Account deleted successfully.", HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
