package com.example.demo.controller;

import com.example.demo.model.extend.StatisticsByHouses;
import com.example.demo.model.extend.StatisticsByMonth;
import com.example.demo.service.statistics.StatisticsByHousesService;
import com.example.demo.service.statistics.StatisticsByMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class StatisticsController {
    @Autowired
    StatisticsByHousesService statisticsByHousesService;
    @Autowired
    StatisticsByMonthService statisticsByMonthService;

    @GetMapping(value = "statisticsByHouses/{id}")
    public ResponseEntity<List<StatisticsByHouses>> statisticsByHouses(@PathVariable(value = "id") Long id) {
        List<StatisticsByHouses> statisticsByHouses = (List<StatisticsByHouses>) statisticsByHousesService.StatisticsByHouses(id);
        return new ResponseEntity<List<StatisticsByHouses>>(statisticsByHouses, HttpStatus.OK);
    }

    @GetMapping(value = "statisticsByMonth/{id}")
    public ResponseEntity<List<StatisticsByMonth>> statisticsByMonth(@PathVariable(value = "id") Long id) {
        List<StatisticsByMonth> statisticsByMonths = (List<StatisticsByMonth>) statisticsByMonthService.StatisticsByMonth(id);
        return new ResponseEntity<List<StatisticsByMonth>>(statisticsByMonths, HttpStatus.OK);
    }
}
