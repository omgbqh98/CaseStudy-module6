package com.example.demo.service.houses;


import com.example.demo.model.House;
import com.example.demo.model.HouseImg;
import com.example.demo.service.IGeneralService;
import org.springframework.stereotype.Service;


public interface IHousesImgService extends IGeneralService<HouseImg> {
    Iterable<HouseImg>findAllByHouseId(House house);

}
