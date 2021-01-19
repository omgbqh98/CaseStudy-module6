package com.example.demo.controller;

import com.example.demo.model.HouseImg;
import com.example.demo.repository.IHousesImgRepository;
import com.example.demo.service.homeowner.IHomeownerService;
import com.example.demo.service.houses.IHousesImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/housesImg/")
public class IHousesImgController {
    @Autowired
    IHousesImgService iHousesImgService;

    @PostMapping(value = "create",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<HouseImg> createHouses(@RequestBody HouseImg houseImg){
        HouseImg houseImg1 = iHousesImgService.save(houseImg);
        return new ResponseEntity<HouseImg>(houseImg1, HttpStatus.OK);
    }
}
