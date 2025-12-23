package com.example.initierespringbd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class ConfigApp {


   @Bean
    Scanner createScanner(){
       return  new Scanner(System.in);
   }
}