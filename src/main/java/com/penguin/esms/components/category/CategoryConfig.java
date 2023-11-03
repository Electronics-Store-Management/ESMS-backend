package com.penguin.esms.components.category;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryConfig {
    @Bean
    CommandLineRunner commandLineRunnerCategoryEntity(CategoryRepo categoryRepo){
        return args -> {};
    }
}
