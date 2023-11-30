package com.penguin.esms.components.saleProduct;

import com.penguin.esms.components.saleBill.SaleBillRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaleProductConfig   {
    @Bean
    CommandLineRunner commandLineRunnerSaleProduct(SaleProductRepo saleProductRepo){
    return args -> {};
}
}
