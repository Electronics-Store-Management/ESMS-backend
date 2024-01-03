package com.penguin.esms.components.statistic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.penguin.esms.components.customer.CustomerEntity;
import com.penguin.esms.components.saleProduct.SaleProductEntity;
import com.penguin.esms.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("statistic")
@RequiredArgsConstructor
@ControllerAdvice
public class StatisticController {
    private final StatisticService service;
    private final StatisticRepository repo;

    @GetMapping("name/{name}")
    public StatisticEntity getName(@PathVariable String name) {
        return service.getByName(name);
    }

    @GetMapping("revenue")
    public ResponseEntity<?> getRevenueByPeriod(@RequestParam long start, @RequestParam long end) throws JsonProcessingException {
        return ResponseEntity.ok(service.revenueByPeriod(new Date(start), new Date(end)));
    }

    @GetMapping("category")
    public ResponseEntity<?> getRevenueByCategory(@RequestParam long start, @RequestParam long end) throws JsonProcessingException {
//        Optional<StatisticEntity> statistic = repo.findByName("getRevenueByCategory" + TimeUtils.getDay(start) + TimeUtils.getDay(end));
//        if (statistic.isEmpty()) {
//            return ResponseEntity.ok(service.revenueByCategory(new Date(start), new Date(end)));
//        }
//        return ResponseEntity.ok(statistic.get());
        return ResponseEntity.ok(service.revenueByCategory(new Date(start), new Date(end)));
    }
    @GetMapping("revenue/date")
    public ResponseEntity<?> getRevenueByDate(@RequestParam long date) throws JsonProcessingException {
        return ResponseEntity.ok(service.getRevenueDate(new Date(date)));
    }

    @GetMapping("cost")
    public ResponseEntity<?> getCostByPeriod(@RequestParam long start, @RequestParam long end) throws JsonProcessingException {
        return ResponseEntity.ok(service.costByPeriod(new Date(start), new Date(end)));
    }
}
