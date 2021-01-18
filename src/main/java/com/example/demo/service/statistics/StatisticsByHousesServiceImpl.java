package com.example.demo.service.statistics;

import com.example.demo.model.extend.StatisticsByHouses;
import com.example.demo.repository.IStatisticsByHouses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsByHousesServiceImpl implements StatisticsByHousesService {
    @Autowired
    IStatisticsByHouses iStatisticsByHouses;

    @Override
    public Iterable<StatisticsByHouses> StatisticsByHouses(Long id) {
        return iStatisticsByHouses.statisticsByHouses(id);
    }
}
