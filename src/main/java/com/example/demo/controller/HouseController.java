package com.example.demo.controller;

import com.example.demo.service.house.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/houses")
public class HouseController {
    @Autowired
    private IHouseService houseService;

    // Lấy tất cả những nhà hiện tại
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable getAllHouses() {
        return houseService.findAllByIsDeletedFalse();
    }




}
