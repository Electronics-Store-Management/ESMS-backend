package com.penguin.esms.components.statistic;

import com.penguin.esms.components.saleBill.SaleBillService;
import com.penguin.esms.components.saleProduct.SaleProductRepo;
import com.penguin.esms.components.supplier.SupplierEntity;
import com.penguin.esms.components.supplier.dto.SupplierDTO;
import com.penguin.esms.entity.Error;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final StatisticRepository repo;
    private final SaleBillService saleBillService;
    private final StatisticRepository statisticRepo;
    public StatisticEntity add(StatisticEntity entity) {
        entity.setStatisticDate(new Date());
        return repo.save(entity);
    }
    public StatisticEntity getByName(String name) {
        return repo.findByName(name).get();
    }
    public StatisticEntity getRevenueByPeriod(Date start, Date end) {

        return null;
    }

}
