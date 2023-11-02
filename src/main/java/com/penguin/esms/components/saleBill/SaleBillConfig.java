package com.penguin.esms.components.saleBill;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaleBillConfig {
    @Bean
    CommandLineRunner commandLineRunnerSaleBill(SaleBillRepo saleBillRepo){
        return args -> {};
    }
}
