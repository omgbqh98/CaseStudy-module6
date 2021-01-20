package com.example.demo.service.house;

import com.example.demo.model.House;
import com.example.demo.repository.IHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HouseServiceImpl implements IHouseService {
    @Autowired
    private IHouseRepository houseRepository;

    @Override
    public Iterable<House> findAllByIsDeletedFalse() {
        return houseRepository.findAllByIsDeletedFalse();
    }

    @Override
    public Iterable<House> findAll() {
        return houseRepository.findAll();
    }

    @Override
    public House save(House house) {
        return houseRepository.save(house);
    }

    @Override
    public Optional<House> findById(Long id) {
        return houseRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
    }

    @Override
    public Iterable<House> findAllByOwnerIdAndDeletedFalse(long ownerId) {
        return houseRepository.findAllByOwnerIdAndDeletedFalse(ownerId);
    }

    @Override
    public Iterable<House> findAllByIsDeleteFalseOderByCreatedAt() {
        return houseRepository.findAllByIsDeletedFalseOderByCreatedAt();
    }

    @Override
    public Iterable<House> findHouseByBedroom(int quantity) {
        return houseRepository.findHouseByBedroom(quantity);
    }

    @Override
    public Iterable<House> findHouseByBathroom(int quantity) {
        return houseRepository.findHouseByBathroom(quantity);
    }

    @Override
    public Iterable<House> findHouseByAddressContaining(String address) {
        return houseRepository.findHouseByAddressContaining(address);
    }
}