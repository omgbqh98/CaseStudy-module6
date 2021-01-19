package com.example.demo.service.rating;

import com.example.demo.model.Booking;
import com.example.demo.model.Rating;
import com.example.demo.repository.IRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingServiceImpl implements IRatingService{
    @Autowired
    private IRatingRepository ratingRepository;

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
        return null;
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
}
