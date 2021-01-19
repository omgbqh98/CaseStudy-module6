package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.model.House;
import com.example.demo.model.Rating;
import com.example.demo.service.booking.IBookingService;
import com.example.demo.service.house.IHouseService;
import com.example.demo.service.rating.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@CrossOrigin("*")
@RequestMapping("/houses")
public class HouseController {
    @Autowired
    private IHouseService houseService;
    @Autowired
    private IBookingService bookingService;
    @Autowired
    private IRatingService ratingService;

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
        house.setCreatedAt(Timestamp.valueOf(java.time.LocalDateTime.now()));
        return houseService.save(house);
    }


    // Xem chi tiết nhà
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<House> getDetailHouse(@PathVariable Long id) {
        House house = houseService.findById(id).get();
        return new ResponseEntity<>(house, HttpStatus.OK);
    }

    // Xem booking của một nhà
    @GetMapping(value="/{id}/bookings",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Iterable<Booking>> getBookingByHouseId(@PathVariable Long id){
        Iterable<Booking> bookings = bookingService.findBookingByHouseId(id);
        return new ResponseEntity<>(bookings,HttpStatus.OK);
    }

    // Update thông tin nhà
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<House> updateHouse(@PathVariable Long id, @RequestBody House house) {
        house.setHouseId(id);
        House updatedhouse = houseService.save(house);
        return new ResponseEntity<>(updatedhouse, HttpStatus.OK);
    }

    //danh sach nhà mới đăng
    @GetMapping("/listHouseNew")
    private ResponseEntity<Iterable<House>> listNewHouse() {
        Iterable<House> houses = houseService.findAllByIsDeleteFalseOderByCreatedAt();
        return new ResponseEntity<>(houses, HttpStatus.OK);
    }

    // Lấy tất cả bình luận của một nhà
    @GetMapping(value = "/{id}/ratings",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Iterable<Rating>> findAllByHouseId_HouseId(@PathVariable Long id){
        Iterable<Rating> ratings = ratingService.findAllByHouseId_HouseId(id);
        return new ResponseEntity<>(ratings,HttpStatus.OK);
    }

    // Lấy tất cả bình luận CHA của một nhà
    @GetMapping(value = "/{id}/parentRatings",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Iterable<Rating>> findAllParentRatingByHouse(@PathVariable Long id){
        Iterable<Rating> ratings = ratingService.findAllParentRatingByHouse(id);
        return new ResponseEntity<>(ratings,HttpStatus.OK);
    }

    // Lấy tất cả bình luận CON của một nhà
    @GetMapping(value = "/{id}/childRatings",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Iterable<Rating>> findAllChildRatingByHouse(@PathVariable Long id){
        Iterable<Rating> ratings = ratingService.findAllChildRatingByHouse(id);
        return new ResponseEntity<>(ratings,HttpStatus.OK);
    }

    // Lấy tất cả bình luận CON theo bình luận cha
    @GetMapping(value = "/childRatings/{parentId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Iterable<Rating>> findAllChildRatingByParentRating(@PathVariable Long parentId){
        Iterable<Rating> ratings = ratingService.findAllChildRatingByParentRating(parentId);
        return new ResponseEntity<>(ratings,HttpStatus.OK);
    }

}
