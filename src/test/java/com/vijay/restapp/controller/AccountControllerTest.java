package com.vijay.restapp.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vijay.restapp.model.Account;
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
		String jsonstring = objectMapper.writeValueAsString(account);
		
		this.mockMvc.perform(
					post("/accounts")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(jsonstring)
					.accept(MediaType.APPLICATION_JSON)
				 	).andDo(print()).andExpect(status().isOk());
    }
}
