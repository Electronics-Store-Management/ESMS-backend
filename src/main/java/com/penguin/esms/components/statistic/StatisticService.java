package com.penguin.esms.components.statistic;

import com.penguin.esms.components.saleBill.SaleBillEntity;
import com.penguin.esms.components.saleBill.SaleBillRepo;
import com.penguin.esms.components.saleBill.SaleBillService;
import com.penguin.esms.components.saleProduct.SaleProductEntity;
import com.penguin.esms.components.saleProduct.SaleProductRepo;
import com.penguin.esms.components.saleProduct.SaleProductService;
import com.penguin.esms.components.supplier.SupplierEntity;
import com.penguin.esms.components.supplier.dto.SupplierDTO;
import com.penguin.esms.entity.Error;
import com.penguin.esms.envers.AuditEnversInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.history.Revision;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final StatisticRepository repo;
    private final SaleBillService saleBillService;
    private final SaleProductService saleProductService;
    private final SaleBillRepo saleBillRepo;
    private final SaleProductRepo saleProductRepo;

    public StatisticEntity add(StatisticEntity entity) {
        entity.setStatisticDate(new Date());
        return repo.save(entity);
    }
    public StatisticEntity getByName(String name) {
        return repo.findByName(name).get();
    }
    public Long revenueByPeriod(Date start, Date end) {
        Long revenue = 0l;
        List<AuditEnversInfo> auditEnversInfoList = (List<AuditEnversInfo>) saleBillService.getAllRevisions(start,end);
        for (AuditEnversInfo i : auditEnversInfoList) {
            SaleBillEntity saleBill = (SaleBillEntity) i.getRevision();
            for (SaleProductEntity t : (List<SaleProductEntity>) saleBill.getSaleProducts()) {
                    revenue += t.getPrice();
                }
        }
        return revenue;
    }

}
