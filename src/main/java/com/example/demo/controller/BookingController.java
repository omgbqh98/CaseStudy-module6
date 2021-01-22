package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.model.House;
import com.example.demo.service.booking.IBookingService;
import com.example.demo.service.house.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class BookingController {
    @Autowired
    IBookingService iBookingService;
    @Autowired
    IHouseService houseService;
    private long oneDay = 88640000;

    @PostMapping(value = "booking-hotel")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        booking.setCreatedAt(Timestamp.valueOf(java.time.LocalDateTime.now()));
        return new ResponseEntity<>(iBookingService.save(booking), HttpStatus.OK);
    }

    @GetMapping(value = "list-booking")
    public ResponseEntity<Iterable<Booking>> getAllBooking() {
        Iterable<Booking> list = iBookingService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "list-booking-by-houseId/{id}")
    public ResponseEntity<List<Booking>> getAllBookingByHouseId(@PathVariable Long id) {
        List<Booking> list = (List<Booking>) iBookingService.findBookingByHouseId(id);
        if (list.isEmpty()) {
            return new ResponseEntity<List<Booking>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Booking>>(list, HttpStatus.OK);
    }

    //check khi qua checkout tu doi trang thai nhà
    @GetMapping("/checkout")
    public ResponseEntity<String> checkout() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        for (Booking bookingFind : iBookingService.findAll()) {
                long checkoutTime = bookingFind.getCheckOut().getTime();
                long currentTime = date.getTime();
                long demoTime = currentTime - checkoutTime;
                if (demoTime > oneDay) {
                    Optional<House> house = houseService.findById(bookingFind.getHouseId().getHouseId());
                    house.get().setStatus(0);
                    houseService.save(house.get());
                }
            }
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/checkHired")
//    public ResponseEntity<String> checkHired() {
//        Calendar cal = Calendar.getInstance();
//        Date date = cal.getTime();
//        for (:) {
//        }
//    }
}
