package com.example.demo.service.house;

<<<<<<< HEAD
public class HouseServiceImpl {
=======
import com.example.demo.model.House;
import com.example.demo.repository.IHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HouseServiceImpl implements IHouseService{
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
>>>>>>> 909aa3511a1d73656efc9c0ee2571584615b74d9
}
