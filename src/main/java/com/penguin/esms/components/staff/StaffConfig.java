package com.penguin.esms.components.staff;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StaffConfig {
    @Bean
    CommandLineRunner commandLineRunnerStaff(StaffRepo staffRepo){
        return args -> {};
    }
}
