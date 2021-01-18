package com.example.demo.repository;

import com.example.demo.model.extend.StatisticsByHouses;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IStatisticsByHouses extends CrudRepository<StatisticsByHouses, Long> {
    @Query(value = "CALL StatisticsByHouses(?1);", nativeQuery = true)
    Iterable<StatisticsByHouses> statisticsByHouses(Long id);
}
