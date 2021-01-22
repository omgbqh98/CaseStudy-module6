package com.example.demo.service.rating;

import com.example.demo.model.Booking;
import com.example.demo.model.House;
import com.example.demo.model.Rating;
import com.example.demo.model.User;
import com.example.demo.repository.IRatingRepository;
import com.example.demo.service.booking.IBookingService;
import com.example.demo.service.house.IHouseService;
import com.example.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements IRatingService{
    @Autowired
    private IRatingRepository ratingRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private IBookingService bookingService;
    @Autowired
    private IHouseService houseService;


    @Override
    public Iterable<Rating> findAllByHouseId_HouseId(Long houseId) {
        return ratingRepository.findAllByHouseId_HouseIdOrderByCreatedAtDesc(houseId);
    }

    @Override
    public Iterable<Rating> findAll() {
        return null;
    }

    @Override
    public Rating save(Rating rating) {
        rating.setCreatedAt(Timestamp.valueOf(java.time.LocalDateTime.now()));
        return ratingRepository.save(rating);
    }

    @Override
    public Optional<Rating> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void remove(Long id) {
    }

    @Override
    public Iterable<Rating> findAllParentRatingByHouse(Long id) {
        return ratingRepository.findAllParentRatingByHouse(id);
    }

    @Override
    public Iterable<Rating> findAllChildRatingByParentRating(Long id) {
        return ratingRepository.findAllChildRatingByParentRating(id);
    }

    @Override
    public Iterable<Rating> findAllByHouseIdParentIdDesc(Long id) {
        return ratingRepository.findAllByHouseIdParentIdDesc(id);
    }

    @Override
    public Iterable<Rating> findAllChildRatingByHouse(Long id) {
        return ratingRepository.findAllChildRatingByHouse(id);
    }

    @Override
    public Iterable<User> findCheckoutUserByHouse(Long id) {
        Iterable<BigInteger> userId = ratingRepository.findCheckoutUserByHouse(id);
        List<User> users = new ArrayList<>();
        for(BigInteger oneId: userId){
            Long idLong = oneId.longValue();
            users.add(userService.findById(idLong).get());
        }
        Iterable<User> userList = users;
        return userList;
    }

    @Override
    public Iterable<User> findCheckedOutAndRatedUserByHouse(Long id) {
        Iterable<BigInteger> userId = ratingRepository.findCheckedOutAndRatedUserByHouse(id);
        List<User> users = new ArrayList<>();
        for(BigInteger oneId: userId){
            Long idLong = oneId.longValue();
            users.add(userService.findById(idLong).get());
        }
        Iterable<User> userList = users;
        return userList;
    }

    @Override
    public Rating createRate(Rating rating) {
        Booking booking = rating.getBookingId();
        Booking booking1 = bookingService.findById(booking.getBookingId()).get();
        booking1.setRated(true);
        bookingService.save(booking1);
        Rating savedRating = save(rating);
        savedRating.setParentId(savedRating.getRatingId());
        // Phần house
        House house = rating.getHouseId();
        Rating addedSaving = save(savedRating);
        house.setAvgRate(avgRateScoreByHouse(house.getHouseId()));
        houseService.save(house);
        return addedSaving;
    }

    @Override
    public Double avgRateScoreByHouse(Long id) {
        return ratingRepository.avgRateScoreByHouse(id);
    }
}
