package com.penguin.esms.components.statistic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatisticRepository extends JpaRepository<StatisticEntity, String> {
    Optional<StatisticEntity> findById(String id);
    Optional<StatisticEntity> findByName(String name);

}
