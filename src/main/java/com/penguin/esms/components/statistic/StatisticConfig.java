package com.penguin.esms.components.statistic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.penguin.esms.components.supplier.SupplierRepo;
import com.penguin.esms.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
@EnableAsync
public class StatisticConfig {
    private final StatisticService service;

    @Bean
    CommandLineRunner commandLineRunnerStatistic(StatisticRepository repo) {
        return args -> {
        };
    }

    //    @Scheduled(cron = "1 * * * * *")
    @Scheduled(fixedRate = 3600000)
    public void scheduledRevenueByPeriod() throws JsonProcessingException {
        if (new Date().getHours() == 0 && new Date().getMinutes() == 15) {
            System.out.println("gio ne");
            System.out.println(new Date().getHours());
            System.out.println(new Date().getMinutes());
            try {
                Double previousDateTime = ((double) (TimeUtils.getDay(new Date()) - 1)) - 7.0 / 24;
                Date previousDateStart = new Date((long) (previousDateTime * 86400000));
                Date previousDateEnd = new Date((long) ((previousDateTime + 1) * 86400000));
                service.revenueByPeriod(previousDateStart, previousDateEnd);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Scheduled(fixedRate = 3600000)
//    @Scheduled(cron = "26 0 * * * ? ", zone = "Asia/Ho_Chi_Minh")
    public void scheduledRevenueByCategory() throws JsonProcessingException {
        if (new Date().getHours() == 0 && new Date().getMinutes() == 10) {
            System.out.println("gio ne");
            System.out.println(new Date().getHours());
            System.out.println(new Date().getMinutes());
            try {
                Double previousDateTime = ((double) (TimeUtils.getDay(new Date()) - 1)) - 7.0 / 24;
                Date previousDateStart = new Date((long) (previousDateTime * 86400000));
                Date previousDateEnd = new Date((long) ((previousDateTime + 1) * 86400000));
                service.revenueByCategory(previousDateStart, previousDateEnd);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Scheduled(fixedRate = 3600000)
//    @Async
//    @Scheduled(cron = "26 0 * * * ? ", zone = "Asia/Ho_Chi_Minh")
    public void scheduledCostByPeriod() throws JsonProcessingException {
        if (new Date().getHours() == 0 && new Date().getMinutes() == 5) {
            System.out.println("gio ne");
            System.out.println(new Date().getHours());
            System.out.println(new Date().getMinutes());
            try {
                Double previousDateTime = ((double) (TimeUtils.getDay(new Date()) - 1)) - 7.0 / 24;
                Date previousDateStart = new Date((long) (previousDateTime * 86400000));
                Date previousDateEnd = new Date((long) ((previousDateTime + 1) * 86400000));
                service.costByPeriod(previousDateStart, previousDateEnd);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
