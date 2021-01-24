package com.example.demo.service.houses;


import com.example.demo.model.House;
import com.example.demo.model.HouseImg;
import com.example.demo.repository.IHousesImgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IHousesImgImpl implements IHousesImgService {
    @Autowired
    IHousesImgRepository iHousesImgRepository;
    @Override
    public Iterable<HouseImg> findAll() {
        return iHousesImgRepository.findAll();
    }

    @Override
    public HouseImg save(HouseImg houseImg) {
        return iHousesImgRepository.save(houseImg);
    }

    @Override
    public Optional<HouseImg> findById(Long id) {
        return iHousesImgRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
iHousesImgRepository.deleteById(id);
    }


    @Override
    public Iterable<HouseImg> findAllByHouseId(House house) {
        return iHousesImgRepository.findAllByHouseId(house);
    }

}
