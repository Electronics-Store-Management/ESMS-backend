package com.penguin.esms.components.warrantyBill;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WarrantyBillConfig {
    @Bean
    CommandLineRunner commandLineRunnerWarrantyBill(WarrantyBillRepo warrantyBillRepo){
        return args -> {};
    }
}
