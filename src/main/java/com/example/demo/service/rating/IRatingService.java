package com.example.demo.service.rating;

import com.example.demo.model.Booking;
import com.example.demo.model.Rating;
import com.example.demo.model.User;
import com.example.demo.repository.IRatingRepository;
import com.example.demo.service.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface IRatingService extends IGeneralService<Rating> {
    Iterable<Rating> findAllByHouseId_HouseId(Long houseId);
    Iterable<Rating> findAllParentRatingByHouse(Long id);
    Iterable<Rating> findAllChildRatingByParentRating(Long id);
    Iterable<Rating> findAllByHouseIdParentIdDesc(Long id);
    Iterable<Rating> findAllChildRatingByHouse(Long id);
    Iterable<User> findCheckoutUserByHouse(Long id);
    Iterable<User> findCheckedOutAndRatedUserByHouse(Long id);
    Rating createRate(Rating rating);
    Double avgRateScoreByHouse(Long id);
}
