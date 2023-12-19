package com.penguin.esms.components.statistic;

import com.penguin.esms.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
public class StatisticEntity extends BaseEntity {
    private String name;
    private String data;
    private Date statisticDate;
}
