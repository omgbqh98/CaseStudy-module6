package com.example.demo.service.rating;

import com.example.demo.model.Rating;
import com.example.demo.repository.IRatingRepository;
import com.example.demo.service.IGeneralService;

public interface IRatingService extends IGeneralService<Rating> {
    Iterable<Rating> findAllByHouseId_HouseId(Long houseId);
}
