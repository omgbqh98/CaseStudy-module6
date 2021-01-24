package com.example.demo.repository;

import com.example.demo.model.extend.StatisticsByHouses;
import com.example.demo.model.extend.StatisticsByMonth;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IStatisticsByMonth extends CrudRepository<StatisticsByMonth, String> {
    @Query(value = "CALL StatisticsByMonth(?1);", nativeQuery = true)
    Iterable<StatisticsByMonth> statisticsByMonth(Long id);
}
