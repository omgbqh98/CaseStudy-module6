package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.model.House;
import com.example.demo.model.Rating;
import com.example.demo.model.extend.Search;
import com.example.demo.model.User;
import com.example.demo.service.booking.IBookingService;
import com.example.demo.service.house.IHouseService;
import com.example.demo.service.rating.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/houses")
public class HouseController {
    @Autowired
    private IHouseService houseService;
    @Autowired
    private IBookingService bookingService;
    private long oneDay = 8640000;
    //    private long oneDay =117280000;
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
    @GetMapping(value = "/{id}/bookings", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Iterable<Booking>> getBookingByHouseId(@PathVariable Long id) {
        Iterable<Booking> bookings = bookingService.findBookingByHouseId(id);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
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

    //lay booking theo id
    @GetMapping("/getBooking/{id}")
    public ResponseEntity<Optional<Booking>> findBookingById(@PathVariable Long id) {
        Optional<Booking> booking = bookingService.findById(id);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    //huy booking truoc 1 ngay
    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Optional<Booking> booking = bookingService.findById(id);
        if (booking == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        long orderTime = booking.get().getCheckIn().getTime();
        long currentTime = date.getTime();
        long timeDemo = orderTime - currentTime;
        if (timeDemo > oneDay && timeDemo > 0) {
            bookingService.delete(id);
//            Optional<House> house1 = houseService.findById(booking.get().getHouseId().getHouseId());
//            house1.get().setStatus(0);
//            houseService.save(house1.get());
        } else {
            return new ResponseEntity<>("Không thể xoá", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // Lấy tất cả bình luận của một nhà
    @GetMapping(value = "/{id}/ratings", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Iterable<Rating>> findAllByHouseId_HouseId(@PathVariable Long id) {
        Iterable<Rating> ratings = ratingService.findAllByHouseId_HouseId(id);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    // Lấy tất cả bình luận CHA của một nhà
    @GetMapping(value = "/{id}/parentRatings", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Iterable<Rating>> findAllParentRatingByHouse(@PathVariable Long id) {
        Iterable<Rating> ratings = ratingService.findAllParentRatingByHouse(id);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    // Lấy tất cả bình luận CON của một nhà
    @GetMapping(value = "/{id}/childRatings", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Iterable<Rating>> findAllChildRatingByHouse(@PathVariable Long id) {
        Iterable<Rating> ratings = ratingService.findAllChildRatingByHouse(id);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    // Lấy tất cả bình luận CON theo bình luận cha
    @GetMapping(value = "/childRatings/{parentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Iterable<Rating>> findAllChildRatingByParentRating(@PathVariable Long parentId) {
        Iterable<Rating> ratings = ratingService.findAllChildRatingByParentRating(parentId);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    //checkIn nha trong history booking
    @GetMapping("/checkIn/{id}")
    public ResponseEntity<String> checkIn(@PathVariable Long id) {
        Optional<Booking> booking = bookingService.findById(id);
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        long checkoutTime = booking.get().getCheckOut().getTime();
        long currentTime = date.getTime();
        long timeDemo = currentTime - checkoutTime;
        if (timeDemo < 88640000) {
            Optional<House> house = houseService.findById(booking.get().getHouseId().getHouseId());
            house.get().setStatus(1);
            houseService.save(house.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //doi trang thai nha sang dang nang cap
    @GetMapping("/upgrade/{id}")
    public ResponseEntity<String> upgrade(@PathVariable Long id) {
        House house = houseService.findById(id).get();
        house.setStatus(3);
        houseService.save(house);
        return new ResponseEntity<>("thanh cong", HttpStatus.OK);
    }

    //doi trang thai nha sang đã thuê
    @GetMapping("hired/{id}")
    public ResponseEntity<String> hired(@PathVariable Long id) {
        House house = houseService.findById(id).get();
        house.setStatus(1);
        houseService.save(house);
        return new ResponseEntity<>("thanh cong", HttpStatus.OK);
    }

    //doi trang thai nha sang da checkin
    @GetMapping("/checkedIn/{id}")
    public ResponseEntity<String> checkedIn(@PathVariable Long id) {
        House house = houseService.findById(id).get();
        house.setStatus(2);
        houseService.save(house);
        return new ResponseEntity<>("thanh cong", HttpStatus.OK);
    }

    //doi trang thai nha sang con trong
    @GetMapping("/empty/{id}")
    public ResponseEntity<String> empty(@PathVariable Long id) {
        House house = houseService.findById(id).get();
        house.setStatus(0);
        houseService.save(house);
        return new ResponseEntity<>("thanh cong", HttpStatus.OK);
    }

    @PostMapping("/search")
    private ResponseEntity<List<House>> searchHouse(@RequestBody Search search) {
        List<House> houses = houseService.searchHouse(search);
        return new ResponseEntity<>(houses, HttpStatus.OK);
    }


    // Lấy tất cả user đã checkout và đã rate nhà
    @GetMapping(value = "/{id}/checkedOutRatedUser", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Iterable<User>> findCheckedOutAndRatedUserByHouse(@PathVariable Long id){
        Iterable<User> users = ratingService.findCheckedOutAndRatedUserByHouse(id);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    // Lấy tất cả user đã checkout
    @GetMapping(value = "/{id}/checkedOutUser", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Iterable<User>> findCheckedOutUserByHouse(@PathVariable Long id){
        Iterable<User> users = ratingService.findCheckoutUserByHouse(id);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    // Tạo comment trong thread theo nhà
    @PostMapping(value = "/createRating", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Rating> createNewRating (@RequestBody Rating rating) {
        Rating newRating = ratingService.save(rating);
        return new ResponseEntity<>(newRating,HttpStatus.OK);
    }

}
