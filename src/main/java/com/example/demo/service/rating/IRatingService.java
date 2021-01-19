package com.example.demo.service.rating;

import com.example.demo.model.Booking;
import com.example.demo.model.Rating;
import com.example.demo.repository.IRatingRepository;
import com.example.demo.service.IGeneralService;
import org.springframework.data.repository.query.Param;

public interface IRatingService extends IGeneralService<Rating> {
    Iterable<Rating> findAllByHouseId_HouseId(Long houseId);
    Iterable<Rating> findAllParentRatingByHouse(Long id);
    Iterable<Rating> findAllChildRatingByParentRating(Long id);
    Iterable<Rating> findAllByHouseIdParentIdDesc(Long id);
    Iterable<Rating> findAllChildRatingByHouse(Long id);
}
