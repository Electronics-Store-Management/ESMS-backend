package com.penguin.esms.components.importProduct;

import com.penguin.esms.components.product.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImportProductConfig {
    @Bean
    CommandLineRunner commandLineRunnerImportProduct(ImportProductRepo importProductRepo){
        return args ->{};
    }
}
