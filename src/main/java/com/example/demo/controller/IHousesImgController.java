package com.example.demo.controller;

import com.example.demo.model.House;
import com.example.demo.model.HouseImg;
import com.example.demo.repository.IHousesImgRepository;
import com.example.demo.service.homeowner.IHomeownerService;
import com.example.demo.service.house.IHouseService;
import com.example.demo.service.houses.IHousesImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/housesImg/")
public class IHousesImgController {
    @Autowired
    IHousesImgService iHousesImgService;
    @Autowired
    IHouseService iHouseService;
    @PostMapping(value = "create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<HouseImg> createHouses(@RequestBody HouseImg houseImg) {
        HouseImg houseImg1 = iHousesImgService.save(houseImg);
        return new ResponseEntity<HouseImg>(houseImg1, HttpStatus.OK);
    }
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<HouseImg> getDetailHouse(@PathVariable Long id) {
        HouseImg houseImg = iHousesImgService.findById(id).get();
        return new ResponseEntity<>(houseImg, HttpStatus.OK);
    }
    @GetMapping(value = "detail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public  ResponseEntity<Iterable<HouseImg>>findByHousesImg(@PathVariable Long id){
       Optional<House> house = iHouseService.findById(id);
        Iterable<HouseImg> houseImgs= iHousesImgService.findAllByHouseId(house.get());
        return  new ResponseEntity<Iterable<HouseImg>>(houseImgs,HttpStatus.OK);
    }
    @DeleteMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public  ResponseEntity<HouseImg>deleteOneHousesImg(@PathVariable Long id){ iHousesImgService.remove(id);
        return  new ResponseEntity<HouseImg>(HttpStatus.OK);
    }
}
