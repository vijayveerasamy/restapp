package com.vijay.restapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class AcountApp 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(AcountApp.class, args);
    }
}
