package com.example.demo.service.statistics;

import com.example.demo.model.extend.StatisticsByHouses;
import com.example.demo.model.extend.StatisticsByMonth;

public interface StatisticsByMonthService {
    Iterable<StatisticsByMonth> StatisticsByMonth(Long id);
}
