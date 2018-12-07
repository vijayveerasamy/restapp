package com.vijay.restapp.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vijay.restapp.model.Account;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AccountControllerTest {

    private ObjectMapper objectMapper = new ObjectMapper();    

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void addAccount() throws Exception {
    	String content = objectMapper.writeValueAsString(newMockAccount());
        
    	MockHttpServletResponse response = mockMvc.perform(post("/accounts/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
        		.andReturn().getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        Account acc = objectMapper.readValue(response.getContentAsString(), Account.class);

        assertNotNull(acc);
        assertTrue(acc.getId() instanceof Long);
    }
    
    @Test
    public void getAccount() throws Exception {
    	MockHttpServletResponse response = null;
    	
    	String content = objectMapper.writeValueAsString(newMockAccount());
        
    	response = mockMvc.perform(post("/accounts/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
        		.andReturn().getResponse();
    	
    	Account acc1 = objectMapper.readValue(response.getContentAsString(), Account.class);
    	
    	response = mockMvc.perform(get("/accounts/"+acc1.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        Account acc2 = objectMapper.readValue(response.getContentAsString(), Account.class);

        assertNotNull(acc2);
        assertEquals(acc1.getId(), acc2.getId());
    }
    
    @Test
    public void updateAccount() throws Exception {
    	MockHttpServletResponse response = null;
    	
    	String content = objectMapper.writeValueAsString(newMockAccount());
        
    	response = mockMvc.perform(post("/accounts/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
        		.andReturn().getResponse();
    	
    	Account acc1 = objectMapper.readValue(response.getContentAsString(), Account.class);
    	
    	acc1.setAccountNumber("987654321");
    	
    	content = objectMapper.writeValueAsString(acc1);
    	
    	response = mockMvc.perform(put("/accounts/"+acc1.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
        		.andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        Account acc2 = objectMapper.readValue(response.getContentAsString(), Account.class);

        assertNotNull(acc2);
        assertEquals(acc1.getId(), acc2.getId());
        assertEquals(acc1.getAccountNumber(), acc2.getAccountNumber());
    }
    
    @Test
    public void accounts() throws Exception {
    	MockHttpServletResponse response = null;
    	
    	String content = objectMapper.writeValueAsString(newMockAccount());
        
    	response = mockMvc.perform(post("/accounts/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
        		.andReturn().getResponse();
    	
    	response = mockMvc.perform(get("/accounts/")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        @SuppressWarnings("unchecked")
		List<Account> accList = (List<Account>) objectMapper.readValue(response.getContentAsString(), 
        								objectMapper.getTypeFactory().constructCollectionType(List.class, Account.class) );

        assertNotNull(accList);
        assertEquals(accList.size(), 1);
    }
    
    private Account newMockAccount() {
		Account account = new Account();

		account.setFirstName("First");
		account.setSecondName("Second");
		account.setAccountNumber("12345678");
		
		return account;
    }
}
