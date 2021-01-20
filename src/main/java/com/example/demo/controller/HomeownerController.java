//package com.example.demo.controller;
//
//
//import com.example.demo.model.Homeowner;
//import com.example.demo.model.House;
//import com.example.demo.model.User;
//import com.example.demo.service.homeowner.IHomeownerService;
//import com.example.demo.service.house.IHouseService;
//import org.aspectj.bridge.Message;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.sql.Timestamp;
//
//@RestController
//@CrossOrigin("*")
//@RequestMapping("/homeowner")
//public class HomeownerController {
//    @Autowired
//    IHomeownerService iHomeownerService;
//    @Autowired
//    IHouseService iHouseService;
//    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public ResponseEntity<Homeowner> getDetailHouse(@PathVariable Long id) {
//        Homeowner homeowner = iHomeownerService.findById(id).get();
//        return new ResponseEntity<>(homeowner, HttpStatus.OK);
//    }
//    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public Homeowner createHomeowner(@RequestBody Homeowner homeowner) {
//        homeowner.setTime(Timestamp.valueOf(java.time.LocalDateTime.now()));
//        return iHomeownerService.save(homeowner);
//    }
////    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
////    @ResponseBody
////    public ResponseEntity<Homeowner> updateHouse(@PathVariable Long id, @RequestBody Homeowner homeowner) {
////        homeowner.setId(id);
////        homeowner.setTime(Timestamp.valueOf(java.time.LocalDateTime.now()));
////        House house =  homeowner.getHouse();
////        if (homeowner.getTime() == house.getCreatedAt()) {
////            return new ResponseEntity<Homeowner>(HttpStatus.OK);
////        }
////        if(homeowner.getTime()  house.getCreatedAt())
////    }
//}
