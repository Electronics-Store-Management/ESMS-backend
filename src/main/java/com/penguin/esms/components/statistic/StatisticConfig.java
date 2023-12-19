package com.penguin.esms.components.statistic;

import com.penguin.esms.components.supplier.SupplierRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StatisticConfig {
    @Bean
    CommandLineRunner commandLineRunnerStatistic(SupplierRepo supplierRepo){
        return args -> {};
    }
}
