package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.service.booking.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin("*")
public class BookingController {
    @Autowired
    IBookingService iBookingService;

    @PostMapping(value = "booking-hotel")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        booking.setCreatedAt(Timestamp.valueOf(java.time.LocalDateTime.now()));
        return new ResponseEntity<>(iBookingService.save(booking), HttpStatus.OK);
    }

    @GetMapping(value = "list-booking")
    public ResponseEntity<List<Booking>> getAllBooking() {
        List<Booking> list = (List<Booking>) iBookingService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<List<Booking>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Booking>>(list, HttpStatus.OK);
    }
}
