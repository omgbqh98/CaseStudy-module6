package com.example.demo.service.house;

import com.example.demo.model.House;
import com.example.demo.service.IGeneralService;

public interface IHouseService extends IGeneralService<House> {
    Iterable<House> findAllByIsDeletedFalse();

    Iterable<House> findAllByOwnerIdAndDeletedFalse(long ownerId);

    Iterable<House> findAllByIsDeleteFalseOderByCreatedAt();

    Iterable<House> findHouseByBedroom(int quantity);

    Iterable<House> findHouseByBathroom(int quantity);

    Iterable<House> findHouseByAddressContaining(String address);
}