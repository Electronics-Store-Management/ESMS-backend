package com.penguin.esms.components.statistic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.penguin.esms.components.supplier.SupplierRepo;
import com.penguin.esms.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class StatisticConfig {
    private final StatisticService service;

    @Bean
    CommandLineRunner commandLineRunnerStatistic(SupplierRepo supplierRepo) {

        return args -> {
        };
    }

    @Async
    @Scheduled(cron = "10 0 * * * ? ", zone = "Asia/Ho_Chi_Minh")
    public void scheduledRevenueByPeriod() throws JsonProcessingException {
        Long previousDateTime = TimeUtils.getDay(new Date()) - 1 + 7 / 24;
        Date previousDateStart = new Date(previousDateTime * 86400000);
        Date previousDateEnd = new Date((previousDateTime + 1) * 86400000);
        service.revenueByPeriod(previousDateStart, previousDateEnd);
    }

    @Async
    @Scheduled(cron = "10 0 * * * ? ", zone = "Asia/Ho_Chi_Minh")
    public void scheduledRevenueByCategory() throws JsonProcessingException {
        Long previousDateTime = TimeUtils.getDay(new Date()) - 1 + 7 / 24;
        Date previousDateStart = new Date(previousDateTime * 86400000);
        Date previousDateEnd = new Date((previousDateTime + 1) * 86400000);
        service.revenueByCategory(previousDateStart, previousDateEnd);
    }
}
