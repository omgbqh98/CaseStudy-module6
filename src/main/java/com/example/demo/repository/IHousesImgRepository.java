package com.example.demo.repository;

import com.example.demo.model.House;
import com.example.demo.model.HouseImg;
import org.springframework.data.repository.CrudRepository;

public interface IHousesImgRepository extends CrudRepository<HouseImg,Long> {
    Iterable<HouseImg>findAllByHouseId(House house);
}
