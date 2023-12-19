package com.penguin.esms.components.statistic;

import com.penguin.esms.components.customer.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("statistic")
@RequiredArgsConstructor
@ControllerAdvice
public class StatisticController {
    private final StatisticService service;
    @GetMapping("name/{name}")
    public StatisticEntity getName(@PathVariable String name) {
        return service.getByName(name);
    }

    @GetMapping("revenue")
    public ResponseEntity<?> getRevenueByPeriod(@RequestParam long start, @RequestParam long end) {
        return ResponseEntity.ok(service.revenueByPeriod(new Date(start), new Date(end)));
    }
    @GetMapping("category")
    public ResponseEntity<?> getRevenueByCategory(@RequestParam long start, @RequestParam long end) {
        return ResponseEntity.ok(service.revenueByCategory(new Date(start), new Date(end)));
    }
    @GetMapping("revenue/date")
    public ResponseEntity<?> getRevenueByDate(@RequestParam long date) {
        return ResponseEntity.ok(service.revenueByDate(date));
    }
    @GetMapping("cost")
    public ResponseEntity<?> getCostByPeriod(@RequestParam long start, @RequestParam long end) {
        return ResponseEntity.ok(service.costByPeriod(new Date(start), new Date(end)));
    }
}
