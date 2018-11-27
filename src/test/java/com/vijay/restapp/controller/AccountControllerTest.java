package com.vijay.restapp.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vijay.restapp.model.Account;
import com.vijay.restapp.service.AccountService;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Mock
    private AccountService accountService;
    
    @MockBean
    private AccountController accountController;
    
    
    @Test
    public void noAccounts() throws Exception {

        this.mockMvc.perform(get("/accounts")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }
    
    @Test
    public void addAccount() throws Exception {
    	
    	Account account = new Account();
    	
    	account.setId(0L);
    	account.setFirstName("First");
    	account.setSecondName("Second");
    	account.setAccountNumber("12345678");
    	
    	ObjectMapper objectMapper = new ObjectMapper();
    	String inputJson = objectMapper.writeValueAsString(account);


    	 MvcResult mvcResult = this.mockMvc.perform(post("/accounts").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
         int status = mvcResult.getResponse().getStatus();
         assertEquals(201, status);
    }
 

}
