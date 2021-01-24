package com.example.demo.controller;
import com.example.demo.model.Booking;
import com.example.demo.model.House;
import com.example.demo.model.User;
import com.example.demo.repository.IBookingRepository;
import com.example.demo.service.booking.IBookingService;
import com.example.demo.service.house.IHouseService;
import com.example.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@PropertySource("classpath:application.properties")
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    IUserService userService;

    @Autowired
    IHouseService houseService;

    @Autowired
    IBookingService bookingService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping()
    public ResponseEntity<Iterable<User>> getAll() {
        Iterable<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Optional<User> findCustomer(@PathVariable("id") Long id) {
        Optional<User> user = userService.findById(id);
        return userService.findById(user.get().getUserId());
    }


    // Cập nhật thông tin user
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User user1 = userService.findByUsername(user.getUsername());
        user1.setFullName(user.getFullName());
        user1.setAddress(user.getAddress());
        user1.setEmail(user.getEmail());
        user1.setPhone(user.getPhone());
        user1.setAvatar(user.getAvatar());
        userService.save(user1);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // Tìm tất cả nhà của một chủ nhà
    @GetMapping("/{id}/ownHouses")
    public ResponseEntity<Iterable<House>> findHousesByOwnerId(@PathVariable long id){
        Iterable<House> houses = houseService.findAllByOwnerIdAndDeletedFalse(id);
        return new ResponseEntity<>(houses,HttpStatus.OK);
    }

    //lich su booking
    @GetMapping("/{id}/booking")
    public ResponseEntity<Iterable<Booking>> findBookingByUserId(@PathVariable Long id) {
        Iterable<Booking> bookings = bookingService.findBookingByUserId(id);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    //Lấy tất cả những booking đã checkout nhưng chưa rate trong 3 tháng gần nhất
    @GetMapping("/{id}/bookingNotRateThreeMonths")
    public ResponseEntity<Iterable<Booking>> findAllBookingNotRatedInThreeMonthsByUser(@PathVariable Long id) {
        Iterable<Booking> bookings = bookingService.findAllBookingNotRatedInThreeMonthsByUser(id);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    // Lấy tất cả những user nào là chủ nhà
    @GetMapping("/owners")
    public ResponseEntity<Iterable<User>> findAllOwners() {
        Iterable<User> owners = userService.findAllOwner();
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }
}
