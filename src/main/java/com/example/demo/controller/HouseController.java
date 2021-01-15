package com.example.demo.controller;

import com.example.demo.model.House;
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

     //Tạo mới nhà
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public House createHouse(@RequestBody House house) {
        return houseService.save(house);
    }




}
