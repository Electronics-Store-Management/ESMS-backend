package com.penguin.esms.components.statistic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.components.category.CategoryService;
import com.penguin.esms.components.importBill.ImportBillEntity;
import com.penguin.esms.components.importBill.ImportBillService;
import com.penguin.esms.components.importProduct.ImportProductEntity;
import com.penguin.esms.components.product.ProductEntity;
import com.penguin.esms.components.saleBill.SaleBillEntity;
import com.penguin.esms.components.saleBill.SaleBillRepo;
import com.penguin.esms.components.saleBill.SaleBillService;
import com.penguin.esms.components.saleProduct.SaleProductEntity;
import com.penguin.esms.components.saleProduct.SaleProductRepo;
import com.penguin.esms.components.saleProduct.SaleProductService;
import com.penguin.esms.components.statistic.dto.CategoryStatisticDTO;
import com.penguin.esms.components.supplier.SupplierEntity;
import com.penguin.esms.components.supplier.dto.SupplierDTO;
import com.penguin.esms.entity.Error;
import com.penguin.esms.envers.AuditEnversInfo;
import com.penguin.esms.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.history.Revision;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final StatisticRepository repo;
    private final SaleBillService saleBillService;
    private final ImportBillService importBillService;
    private final SaleProductService saleProductService;
    private final SaleBillRepo saleBillRepo;
    private final SaleProductRepo saleProductRepo;

    public StatisticEntity add(String name, Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(object);
        StatisticEntity entity = new StatisticEntity(name, jsonString, new Date());
        return repo.save(entity);
    }

    public StatisticEntity getByName(String name) {
        return repo.findByName(name).get();
    }

    public Long revenueByPeriod(Date start, Date end) throws JsonProcessingException {
        Long revenue = 0l;
        Integer quantity = 0;
        List<AuditEnversInfo> auditEnversInfoList = (List<AuditEnversInfo>) saleBillService.getAllRevisions(start, end);
        for (AuditEnversInfo i : auditEnversInfoList) {
            SaleBillEntity saleBill = (SaleBillEntity) i.getRevision();
            for (SaleProductEntity t : (List<SaleProductEntity>) saleBill.getSaleProducts()) {
                try {
                    quantity += t.getQuantity();
                    revenue += t.getPrice() * t.getQuantity();
                } catch (NullPointerException e) {
                }
            }
        }
        add("revenueByPeriod" + TimeUtils.getDay(start) + TimeUtils.getDay(end), revenue);
        return revenue;
    }

    public List<?> revenueByCategory(Date start, Date end) throws JsonProcessingException {
        CategoryStatisticDTO dto = new CategoryStatisticDTO();
        Map<String, CategoryStatisticDTO> map = new HashMap<>();
        List<AuditEnversInfo> auditEnversInfoList = (List<AuditEnversInfo>) saleBillService.getAllRevisions(start, end);
        for (AuditEnversInfo i : auditEnversInfoList) {
            SaleBillEntity saleBill = (SaleBillEntity) i.getRevision();
            for (SaleProductEntity t : saleBill.getSaleProducts()) {
                try {
                    CategoryEntity category = t.getProduct().getCategory();
                    dto = map.get(category.getName());
                    if (dto == null) {
                        dto = new CategoryStatisticDTO();
                        dto.setName(category.getName());
                        dto.setRevenue(0L);
                        dto.setQuantity(0);
                    }
                    dto.setQuantity(dto.getQuantity() + t.getQuantity());
                    dto.setRevenue(dto.getRevenue() + t.getQuantity() * t.getPrice());
                    map.put(category.getName(), dto);
                } catch (NullPointerException e) {
                }
            }
        }
        add("revenueByCategory" + TimeUtils.getDay(start) + TimeUtils.getDay(end), Arrays.asList(map.entrySet().toArray()));
        return Arrays.asList(map.entrySet().toArray());
    }

    public Long revenueByDate(Long date) throws JsonProcessingException {
        Long revenue = 0l;
        Integer quantity = 0;
        List<AuditEnversInfo> auditEnversInfoList = (List<AuditEnversInfo>) saleBillService.getAll();
        for (AuditEnversInfo i : auditEnversInfoList) {
            if (TimeUtils.getDay(i.getTimestamp()) == TimeUtils.getDay(date)) {
                SaleBillEntity saleBill = (SaleBillEntity) i.getRevision();
                for (SaleProductEntity t : (List<SaleProductEntity>) saleBill.getSaleProducts()) {
                    try {
                        quantity += t.getQuantity();
                        revenue += t.getPrice() * t.getQuantity();
                    } catch (NullPointerException e) {
                    }
                }
            }
        }
        add("revenueByDate" + TimeUtils.getDay(date), revenue);
        return revenue;
    }

    public Long costByPeriod(Date start, Date end) throws JsonProcessingException {
        Long cost = 0l;
        Integer quantity = 0;
        List<AuditEnversInfo> auditEnversInfoList = (List<AuditEnversInfo>) importBillService.getAllRevisions(start, end);
        for (AuditEnversInfo i : auditEnversInfoList) {
            ImportBillEntity importBill = (ImportBillEntity) i.getRevision();
            for (ImportProductEntity t : (List<ImportProductEntity>) importBill.getImportProducts()) {
                try {
                    quantity += t.getQuantity();
                    cost += t.getPrice() * t.getQuantity();
                } catch (NullPointerException e) {
                }
            }
        }
        add("costByPeriod" + TimeUtils.getDay(start) + TimeUtils.getDay(end), cost);
        return cost;
    }

}
