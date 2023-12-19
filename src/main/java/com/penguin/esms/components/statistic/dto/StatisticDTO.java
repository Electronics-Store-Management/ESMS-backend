package com.penguin.esms.components.statistic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDTO {
    private String name;
    private Long revenue;
    private Long cost;

    private Integer quantity;

}
