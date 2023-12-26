package com.penguin.esms.components.statistic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.penguin.esms.components.importBill.ImportBillService;
import com.penguin.esms.components.saleBill.SaleBillRepo;
import com.penguin.esms.components.saleBill.SaleBillService;
import com.penguin.esms.components.saleProduct.SaleProductRepo;
import com.penguin.esms.components.saleProduct.SaleProductService;
import com.penguin.esms.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        StatisticEntity entity = new StatisticEntity(name, jsonString, new Date(), TimeUtils.getDay(new Date()));
        return repo.save(entity);
    }
//
//    public StatisticEntity getByName(String name) {
//        return repo.findByName(name).get();
//    }
//
//    public StatisticDTO getRevenuePeriod(Date start, Date end) throws JsonProcessingException {
//        StatisticDTO dto = new StatisticDTO(null, 0l, 0l, 0);
//        Map<String, StatisticDTO> map = new HashMap<>();
//        for (Long i = TimeUtils.getDay(start); i < TimeUtils.getDay(end); i++) {
//            Optional<StatisticEntity> statisticEntityOptional = repo.findByName("revenueByPeriod" + (i - 1) + i);
//            ObjectMapper objectMapper = new ObjectMapper();
//            try {
//                StatisticDTO statistic = objectMapper.readValue(statisticEntityOptional.get().getData(), StatisticDTO.class);
//                dto.setQuantity(dto.getQuantity() + statistic.getQuantity());
//                dto.setRevenue(dto.getRevenue() + statistic.getRevenue());
//            }catch (NoSuchElementException s){}
//
//        }
//        return dto;
//    }
//
//    public StatisticDTO getRevenueDate(Date date) throws JsonProcessingException {
//        Long i = TimeUtils.getDay(date);
//        StatisticDTO dto = new StatisticDTO(null, 0l, 0l, 0);
//        Map<String, StatisticDTO> map = new HashMap<>();
//        Optional<StatisticEntity> statisticEntityOptional = repo.findByName("revenueByDate" + i);
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            StatisticDTO statistic = objectMapper.readValue(statisticEntityOptional.get().getData(), StatisticDTO.class);
//            dto.setQuantity(dto.getQuantity() + statistic.getQuantity());
//            dto.setRevenue(dto.getRevenue() + statistic.getRevenue());
//        } catch (NoSuchElementException s) {
//        }
//
//        return dto;
//    }
////    @Bean
////    @Scheduled(cron = "39 1 * * * * ", zone = "Asia/Ho_Chi_Minh")
////    public void scheduledRevenueByPeriod() throws JsonProcessingException {
////        System.out.println("loz que");
////        Double previousDateTime = ((double) (TimeUtils.getDay(new Date()) - 1)) - 7.0 / 24;
////        Date previousDateStart = new Date((long) (previousDateTime * 86400000));
////        Date previousDateEnd = new Date((long) ((previousDateTime + 1) * 86400000));
////        revenueByPeriod(previousDateStart, previousDateEnd);
////    }
//    public StatisticDTO revenueByPeriod(Date start, Date end) throws JsonProcessingException {
//        StatisticDTO dto = new StatisticDTO(null, 0l, 0l, 0);
//        Map<String, StatisticDTO> map = new HashMap<>();
//        List<AuditEnversInfo> auditEnversInfoList = (List<AuditEnversInfo>) saleBillService.getAllRevisions(start, end);
//        for (AuditEnversInfo i : auditEnversInfoList) {
//            SaleBillEntity saleBill = (SaleBillEntity) i.getRevision();
//            for (SaleProductEntity t : (List<SaleProductEntity>) saleBill.getSaleProducts()) {
//                try {
//                    dto.setQuantity(dto.getQuantity() + t.getQuantity());
//                    dto.setRevenue(dto.getRevenue() + t.getPrice() * t.getQuantity());
//                } catch (NullPointerException e) {
//                }
//            }
//        }
//        add("revenueByPeriod" + TimeUtils.getDay(start) + TimeUtils.getDay(end), dto);
//        return dto;
//    }
//
//    public List<?> revenueByCategory(Date start, Date end) throws JsonProcessingException {
//        StatisticDTO dto = new StatisticDTO();
//        Map<String, StatisticDTO> map = new HashMap<>();
//        List<AuditEnversInfo> auditEnversInfoList = (List<AuditEnversInfo>) saleBillService.getAllRevisions(start, end);
//        for (AuditEnversInfo i : auditEnversInfoList) {
//            SaleBillEntity saleBill = (SaleBillEntity) i.getRevision();
//            for (SaleProductEntity t : saleBill.getSaleProducts()) {
//                try {
//                    CategoryEntity category = t.getProduct().getCategory();
//                    dto = map.get(category.getName());
//                    if (dto == null) {
//                        dto = new StatisticDTO();
//                        dto.setName(category.getName());
//                        dto.setRevenue(0L);
//                        dto.setQuantity(0);
//                    }
//                    dto.setQuantity(dto.getQuantity() + t.getQuantity());
//                    dto.setRevenue(dto.getRevenue() + t.getQuantity() * t.getPrice());
//                    map.put(category.getName(), dto);
//                } catch (NullPointerException e) {
//                }
//            }
//        }
//        add("revenueByCategory" + TimeUtils.getDay(start) + TimeUtils.getDay(end), Arrays.asList(map.entrySet().toArray()));
//        return Arrays.asList(map.entrySet().toArray());
//    }
//
//    public StatisticDTO costByPeriod(Date start, Date end) throws JsonProcessingException {
//        StatisticDTO dto = new StatisticDTO(null, 0l, 0l, 0);
//        Map<String, StatisticDTO> map = new HashMap<>();
//        List<AuditEnversInfo> auditEnversInfoList = (List<AuditEnversInfo>) importBillService.getAllRevisions(start, end);
//        for (AuditEnversInfo i : auditEnversInfoList) {
//            ImportBillEntity importBill = (ImportBillEntity) i.getRevision();
//            for (ImportProductEntity t : (List<ImportProductEntity>) importBill.getImportProducts()) {
//                try {
//                    dto.setQuantity(dto.getQuantity() + t.getQuantity());
//                    dto.setCost(dto.getCost() + t.getPrice() * t.getQuantity());
//                } catch (NullPointerException e) {
//                }
//            }
//        }
//        add("costByPeriod" + TimeUtils.getDay(start) + TimeUtils.getDay(end), dto);
//        return dto;
//    }
//
//    public StatisticDTO getCostPeriod(Date start, Date end) throws JsonProcessingException {
//        StatisticDTO dto = new StatisticDTO(null, 0l, 0l, 0);
//        Map<String, StatisticDTO> map = new HashMap<>();
//        for (Long i = TimeUtils.getDay(start); i < TimeUtils.getDay(end); i++) {
//            Optional<StatisticEntity> statisticEntityOptional = repo.findByName("costByPeriod" + (i - 1) + i);
//            ObjectMapper objectMapper = new ObjectMapper();
//            try {
//                StatisticDTO statistic = objectMapper.readValue(statisticEntityOptional.get().getData(), StatisticDTO.class);
//                dto.setQuantity(dto.getQuantity() + statistic.getQuantity());
//                dto.setRevenue(dto.getCost() + statistic.getCost());
//            } catch (NoSuchElementException s) {
//            }
//        }
//        return dto;
//    }
}
