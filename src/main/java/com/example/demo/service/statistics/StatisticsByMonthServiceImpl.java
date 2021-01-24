package com.example.demo.service.statistics;

import com.example.demo.model.extend.StatisticsByMonth;
import com.example.demo.repository.IStatisticsByHouses;
import com.example.demo.repository.IStatisticsByMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsByMonthServiceImpl implements StatisticsByMonthService {
    @Autowired
    IStatisticsByMonth iStatisticsByMonth;

    @Override
    public Iterable<StatisticsByMonth> StatisticsByMonth(Long id) {
        return iStatisticsByMonth.statisticsByMonth(id);
    }
}
